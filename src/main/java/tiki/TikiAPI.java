package tiki;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import tiki.dto.*;
import tiki.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class TikiAPI {
    public static void main(String[] args) {
        try {
            Gson gson = new Gson();
            String categoryJson = Jsoup
                    .connect("https://tiki.vn/api/personalish/v1/blocks/categories?block_code=featured_categories")
                    .ignoreContentType(true)
                    .execute()
                    .body();
            if (categoryJson != null) {
                CategoryListAPIDto categoryList = gson.fromJson(categoryJson, CategoryListAPIDto.class);
                List<Category> categoryListDB = categoryList.getItems().stream().map(i -> new Category(i.getId(), i.getName())).collect(Collectors.toList());

                // Start handle category
                // End handle category
                for (CategoryAPIDto categoryAPIDto : categoryList.getItems()) {
                    if ("TIKINGON".equalsIgnoreCase(categoryAPIDto.getName())) {
                        continue;
                    }
                    int productPage = 1;
                    while(true) {
                        // Get Product by category ID
                        String jsonProduct = Jsoup
                                .connect("https://tiki.vn/api/personalish/v1/blocks/listings?limit=48&category=" + categoryAPIDto.getId() + "&page=" + productPage)
                                .ignoreContentType(true)
                                .execute()
                                .body();
                        ProductListAPIDto productData = gson.fromJson(jsonProduct, ProductListAPIDto.class);
                        if (productData == null || productData.getData().isEmpty()) {
                            break;
                        }
                        // Start handle product
                        // End handle product
                        productPage++;

                        // get product relate by each product

                        // Get Review by each product
                        for (ProductDataAPIDto product : productData.getData()) {
                            int reviewPage = 1;
                            while (true) {
                                String jsonReview = Jsoup
                                        .connect("https://tiki.vn/api/v2/reviews?limit=5&include=comments,contribute_info&page=" + reviewPage + "&product_id=" + product.getId())
                                        .ignoreContentType(true)
                                        .execute()
                                        .body();
                                ReviewListAPIDto reviewList = gson.fromJson(jsonReview, ReviewListAPIDto.class);
                                if (reviewList == null || reviewList.getReviewAPIDtoList().isEmpty()) {
                                    break;
                                }
                                // Start handle review
                                // End handle review
                                reviewPage++;
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
