package org.springframework.ai.vectorstore.pgvector.autoconfigure;

import org.springframework.ai.embedding.BatchingStrategy;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Bean definitions for {@link PgVectorStoreAutoConfiguration}.
 */
@Generated
public class PgVectorStoreAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'pgVectorStoreAutoConfiguration'.
   */
  public static BeanDefinition getPgVectorStoreAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PgVectorStoreAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(PgVectorStoreAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'pgVectorStoreBatchingStrategy'.
   */
  private static BeanInstanceSupplier<BatchingStrategy> getPgVectorStoreBatchingStrategyInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<BatchingStrategy>forFactoryMethod(PgVectorStoreAutoConfiguration.class, "pgVectorStoreBatchingStrategy")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.vectorstore.pgvector.autoconfigure.PgVectorStoreAutoConfiguration", PgVectorStoreAutoConfiguration.class).pgVectorStoreBatchingStrategy());
  }

  /**
   * Get the bean definition for 'pgVectorStoreBatchingStrategy'.
   */
  public static BeanDefinition getPgVectorStoreBatchingStrategyBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(BatchingStrategy.class);
    beanDefinition.setFactoryBeanName("org.springframework.ai.vectorstore.pgvector.autoconfigure.PgVectorStoreAutoConfiguration");
    beanDefinition.setInstanceSupplier(getPgVectorStoreBatchingStrategyInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'vectorStore'.
   */
  private static BeanInstanceSupplier<PgVectorStore> getVectorStoreInstanceSupplier() {
    return BeanInstanceSupplier.<PgVectorStore>forFactoryMethod(PgVectorStoreAutoConfiguration.class, "vectorStore", JdbcTemplate.class, EmbeddingModel.class, PgVectorStoreProperties.class, ObjectProvider.class, ObjectProvider.class, BatchingStrategy.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.vectorstore.pgvector.autoconfigure.PgVectorStoreAutoConfiguration", PgVectorStoreAutoConfiguration.class).vectorStore(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'vectorStore'.
   */
  public static BeanDefinition getVectorStoreBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PgVectorStore.class);
    beanDefinition.setFactoryBeanName("org.springframework.ai.vectorstore.pgvector.autoconfigure.PgVectorStoreAutoConfiguration");
    beanDefinition.setInstanceSupplier(getVectorStoreInstanceSupplier());
    return beanDefinition;
  }
}
