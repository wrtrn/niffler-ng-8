package guru.qa.niffler.jupiter;

import guru.qa.niffler.api.SpendApiClient;
import guru.qa.niffler.model.CategoryJson;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

public class CategoryExtension implements BeforeEachCallback, ParameterResolver, AfterTestExecutionCallback {

    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(CategoryExtension.class);
    private final SpendApiClient spendApiClient = new SpendApiClient();

    @Override
    public void beforeEach(ExtensionContext context) {
        AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), Category.class)
                .ifPresent(anno -> {

                    CategoryJson categoryJson = new CategoryJson(
                            null,
                            RandomStringUtils.randomAlphanumeric(6),
                            anno.username(),
                            anno.archived()
                    );

                    CategoryJson created = spendApiClient.addCategory(categoryJson);

                    if (anno.archived()) {
                        CategoryJson archivedCategory = new CategoryJson(
                                created.id(),
                                created.name(),
                                created.username(),
                                true
                        );

                        created = spendApiClient.updateCategory(archivedCategory);
                    }
                    context.getStore(NAMESPACE).put(context.getUniqueId(), created);
                });
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(CategoryJson.class);
    }

    @Override
    public CategoryJson resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), CategoryJson.class);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {

        AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), Category.class)
                .ifPresent(anno -> {
                    CategoryJson category =
                            context.getStore(NAMESPACE).get(context.getUniqueId(),
                                    CategoryJson.class);
                    if (category != null && !category.archived()) {
                        CategoryJson updatedCategory = new CategoryJson(
                                category.id(),
                                category.name(),
                                category.username(),
                                true
                        );
                        spendApiClient.updateCategory(updatedCategory);
                    }
                });
    }
}
