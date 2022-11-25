import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.saakyan.PersonalManager_.DataCategories;
import ru.saakyan.PersonalManager_.Product;

public class DataCategoriesTest {
    @DisplayName("Проверка определения категории продукта")
    @Test
    public void testDetermineCategory() throws Exception {
        DataCategories dataCategories = new DataCategories();
        Product product = new Product("сахар", "2023.01.01", 3000.);
        Assertions.assertEquals("другое", dataCategories.determineCategory(product));
    }

    @DisplayName("Проверка включения объекта Product в Map с категориями и продуктами")
    @Test
    public void testAccept() throws Exception {
        DataCategories dataCategories = new DataCategories();
        int valueBegin = dataCategories.getProductsForCategory().size();
        Product productAdd = new Product("сахар", "2023.01.01", 3000.);
        dataCategories.accept(productAdd);
        int valueFinish = dataCategories.getProductsForCategory().size();
        int difference = valueFinish - valueBegin;
        Assertions.assertEquals(1, difference);
    }

    @DisplayName("Проверка обработки нового продукта")
    @Test
    public void TestProcessing() throws Exception {
        DataCategories dataCategories = new DataCategories();
        String resultExpected = "{\"maxCategory\":{\"category\":\"другое\",\"sum\":4000.0}}";
        Product product = new Product("сахар", "2023.01.01", 4000.);
        dataCategories.processing(product);
        Gson gson = new Gson();
        String resultActual = gson.toJson(dataCategories);
        Assertions.assertEquals(resultExpected, resultActual);
    }

    @DisplayName("Проверка поиска max категории по сумме")
    @Test
    public void findMaxCategory() throws Exception {
        DataCategories dataCategories = new DataCategories();
        Product product = new Product("сахар", "2023.01.01", 4000.);
        dataCategories.processing(product);
        DataCategories.MaxCategory maxCategory = new DataCategories().new MaxCategory("другое", 4000.0);
        Assertions.assertEquals(maxCategory, dataCategories.getMaxCategory());
    }
}