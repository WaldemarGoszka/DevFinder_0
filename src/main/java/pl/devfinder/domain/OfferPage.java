package pl.devfinder.domain;

import lombok.*;
import org.springframework.data.domain.Sort;

@Data
public class OfferPage {
    Integer pageNumber = 0;
    Integer pageSize = 3;
    Sort.Direction sortDirection = Sort.Direction.ASC;
    String sortBy = "title";

//    public OfferPage() {
//        this.pageNumber = 1;
//        this.pageSize = 10;
//        this.sortDirection = Sort.Direction.ASC;
//        this.sortBy = "title";
//    }
}
