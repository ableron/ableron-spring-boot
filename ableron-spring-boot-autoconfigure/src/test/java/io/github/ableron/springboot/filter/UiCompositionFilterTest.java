package io.github.ableron.springboot.filter;

import io.github.ableron.Ableron;
import io.github.ableron.AbleronConfig;
import io.github.ableron.springboot.autoconfigure.AbleronAutoConfiguration;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ContextConfiguration(classes = AbleronAutoConfiguration.class)
public class UiCompositionFilterTest {

  @Test
  public void shouldApplyUiComposition() throws ServletException, IOException {
    // given
    Ableron ableron = new Ableron(AbleronConfig.builder().build());
    UiCompositionFilter uiCompositionFilter = new UiCompositionFilter(ableron);
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();
    MockFilterChain filterChain = new MockFilterChain(mock(HttpServlet.class), uiCompositionFilter, new OutputGeneratingFilter(
      "<ableron-include src=\"foo\">fallback</ableron-include>"
    ));

    // when
    filterChain.doFilter(request, response);

    // then
    assertEquals("fallback", response.getContentAsString());
  }

  @Test
  public void shouldNotApplyUiCompositionIfDisabled() throws ServletException, IOException {
    // given
    Ableron ableron = new Ableron(AbleronConfig.builder().enabled(false).build());
    UiCompositionFilter uiCompositionFilter = new UiCompositionFilter(ableron);
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();
    MockFilterChain filterChain = new MockFilterChain(mock(HttpServlet.class), uiCompositionFilter, new OutputGeneratingFilter(
      "<ableron-include src=\"foo\">fallback</ableron-include>"
    ));

    // when
    filterChain.doFilter(request, response);

    // then
    assertEquals("<ableron-include src=\"foo\">fallback</ableron-include>", response.getContentAsString());
  }

  @Test
  public void shouldNotApplyUiCompositionIfContentTypeIsNotTextHtml() throws ServletException, IOException {
    // given
    Ableron ableron = new Ableron(AbleronConfig.builder().build());
    UiCompositionFilter uiCompositionFilter = new UiCompositionFilter(ableron);
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();
    MockFilterChain filterChain = new MockFilterChain(mock(HttpServlet.class), uiCompositionFilter, new OutputGeneratingFilter(
      "<ableron-include src=\"foo\">fallback</ableron-include>", "application/json"
    ));

    // when
    filterChain.doFilter(request, response);

    // then
    assertEquals("<ableron-include src=\"foo\">fallback</ableron-include>", response.getContentAsString());
  }

  static class OutputGeneratingFilter implements Filter {

    private final String content;
    private final String contentType;

    public OutputGeneratingFilter(String content) {
      this(content, MediaType.TEXT_HTML_VALUE);
    }

    public OutputGeneratingFilter(String content, String contentType) {
      this.content = content;
      this.contentType = contentType;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
      servletResponse.setContentType(contentType);
      servletResponse.getOutputStream().print(content);
    }
  }
}
