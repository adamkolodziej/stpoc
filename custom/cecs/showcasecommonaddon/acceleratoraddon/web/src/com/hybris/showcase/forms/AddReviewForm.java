package com.hybris.showcase.forms;

import javax.validation.constraints.*;

/**
 * Created by miroslaw.szot@sap.com on 2015-03-17.
 */
public class AddReviewForm {
    private String title;
    private String description;
    private int rating;
    private String name;
    private String product;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull(message = "{review.form.err.notNull}")
    @Size(min = 1, message = "{review.form.err.notNull}")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "{review.form.err.notNull}")
    @Min(value = 1, message = "{review.form.err.invalid}")
    @Max(value = 5, message = "{review.form.err.invalid}")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @NotNull(message = "{review.form.err.notNull}")
    @Size(min = 1, max = 255, message = "{review.form.err.notNull}")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min = 1)
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
