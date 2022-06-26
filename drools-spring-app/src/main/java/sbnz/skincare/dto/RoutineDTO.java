package sbnz.skincare.dto;

import sbnz.skincare.facts.Routine;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class RoutineDTO {

    private LocalDate startDate;

    private List<ProductDTO> products;

    public RoutineDTO() {
    }

    public RoutineDTO(Routine routine) {
        this.startDate = routine.getStartDate();
        this.products = routine.getProducts().stream().map(ProductDTO::new).collect(Collectors.toList());

    }

    public RoutineDTO(LocalDate startDate, List<ProductDTO> products) {
        this.startDate = startDate;
        this.products = products;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
