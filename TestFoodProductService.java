package Testing;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.entity.FoodCategory;
import com.entity.FoodProduct;
import com.hibernate.Hibernateutil;
import com.service.FoodcategoryService;
import com.service.FoodprodService;
import com.serviceimpl.FoodcategoryServiceimpl;
import com.serviceimpl.FoodprodServiceimpl;

public class TestFoodProductService {

	static FoodprodService foodprodService;
	static FoodcategoryService foodcategoryService;

	@BeforeClass
	public static void setUp() {
		// Session session = Hibernateutil.getSession();
		foodprodService = new FoodprodServiceimpl();
		foodcategoryService = new FoodcategoryServiceimpl();
	}

	@Test
	public void testSaveFoodproduct() {
		FoodCategory foodcategory = foodcategoryService.findFoodCategoryById(1);
		FoodProduct foodproduct = new FoodProduct(7, "Paneer", 200, foodcategory);
		assertTrue(foodprodService.saveFoodproduct(foodproduct));
	}

	@Test
	public void testGetfoodproductById() {
		FoodProduct foodproduct = foodprodService.findFoodProductById(1);
		assertNotNull(foodproduct);
		assertTrue(foodproduct.getfoodProductId() == 1);
	}

	@Test
	public void testGetAllproducts() {
		List<FoodProduct> foodproduct = foodprodService.findAll();
		assertNotNull(foodproduct);
	}

	@Test
	public void testUpdatefoodprodct() {
		FoodCategory foodcategory = foodcategoryService.findFoodCategoryById(1);
		boolean foodproduct = foodprodService.updateFoodProductById(1, "paneer", 1, foodcategory);
		assertTrue(foodproduct);
	}

	@Test
	public void testDeletefoodprodct() {
		boolean foodproduct = foodprodService.removeFoodprodById(1);
		assertTrue(foodproduct);
	}

	@AfterClass
	public static void tearDown() {
		// sessionFactory.close();
	}
}
