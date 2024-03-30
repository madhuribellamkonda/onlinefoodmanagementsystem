package Testing;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.entity.FoodCategory;
import com.hibernate.Hibernateutil;
import com.service.FoodcategoryService;
import com.serviceimpl.FoodcategoryServiceimpl;

public class TestFoodCategoryService {

	static FoodcategoryService foodcategoryService;

	@BeforeClass
	public static void setUp() {
		// Session session = Hibernateutil.getSession();
		foodcategoryService = new FoodcategoryServiceimpl();
	}

	@Test
	public void testSaveFoodcatgeory() {
		FoodCategory foodcategory = new FoodCategory(2, "staters");
		assertTrue(foodcategoryService.saveFoodCategory(foodcategory));
	}

	@Test
	public void testGetfoodcategoryById() {
		FoodCategory foodcategory = foodcategoryService.findFoodCategoryById(1);
		assertNotNull(foodcategory);
		assertTrue(foodcategory.getfoodCategoryId() == 1);
	}

	@Test
	public void testGetAllcategoryes() {
		List<FoodCategory> foodcategory = foodcategoryService.findAll();
		assertNotNull(foodcategory);
	}

	@Test
	public void testUpdatefoodcategory() {
		boolean foodcategory = foodcategoryService.updateFoodCategory(1, "desserts");
		assertTrue(foodcategory);
	}

	@Test
	public void testDeletefoodcategory() {
		boolean foodcategory = foodcategoryService.removeFoodCategoryById(1);
		assertTrue(foodcategory);
	}

	@AfterClass
	public static void tearDown() {
		// foodcategoryServic.close();
	}

}
