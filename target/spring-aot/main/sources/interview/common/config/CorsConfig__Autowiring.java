package interview.common.config;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link CorsConfig}.
 */
@Generated
public class CorsConfig__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static CorsConfig apply(RegisteredBean registeredBean, CorsConfig instance) {
    AutowiredFieldValueResolver.forRequiredField("allowedOrigins").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
