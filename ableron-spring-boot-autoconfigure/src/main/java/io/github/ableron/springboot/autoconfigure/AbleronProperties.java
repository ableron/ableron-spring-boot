package io.github.ableron.springboot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "ableron")
public class AbleronProperties {

  /**
   * Whether Ableron UI composition is enabled.
   * Defaults to true.
   */
  private boolean enabled = true;

  /**
   * Timeout for requesting fragments.
   * Defaults to 3 seconds.
   */
  private long fragmentRequestTimeoutMillis = Duration.ofSeconds(3).toMillis();

  /**
   * Duration to cache fragments in case no caching information is provided
   * along the response, i.e. neither Cache-Control nor Expires header.
   * Defaults to 5 minutes.
   */
  private long fragmentDefaultCacheDurationMillis = Duration.ofMinutes(5).toMillis();

  /**
   * Maximum size in bytes the fragment cache may have.
   * Defaults to 10 MB.
   */
  private long cacheMaxSizeInBytes = 1024 * 1024 * 10;

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public long getFragmentRequestTimeoutMillis() {
    return fragmentRequestTimeoutMillis;
  }

  public void setFragmentRequestTimeoutMillis(long fragmentRequestTimeoutMillis) {
    this.fragmentRequestTimeoutMillis = fragmentRequestTimeoutMillis;
  }

  public long getFragmentDefaultCacheDurationMillis() {
    return fragmentDefaultCacheDurationMillis;
  }

  public void setFragmentDefaultCacheDurationMillis(long fragmentDefaultCacheDurationMillis) {
    this.fragmentDefaultCacheDurationMillis = fragmentDefaultCacheDurationMillis;
  }

  public long getCacheMaxSizeInBytes() {
    return cacheMaxSizeInBytes;
  }

  public void setCacheMaxSizeInBytes(long cacheMaxSizeInBytes) {
    this.cacheMaxSizeInBytes = cacheMaxSizeInBytes;
  }
}
