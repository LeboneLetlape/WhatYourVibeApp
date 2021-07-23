package za.co.whatyourvibe.LogicLayer.Data;

import java.util.ArrayList;
import java.util.List;

import za.co.whatyourvibe.LogicLayer.Models.Category;

public class CategoryRepo {
    public static List<Category> GetCategories()
    {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Sport"));
        categories.add(new Category("Conference"));
        categories.add(new Category("Expos"));
        categories.add(new Category("Concerts"));
        categories.add(new Category("Festivals"));
        categories.add(new Category("Performing Arts"));
        categories.add(new Category("Community"));
        return categories;
    }
}
