package sbnz.skincare.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import sbnz.skincare.facts.Routine;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class RoutineWithReactionDTO {

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    private List<ProductReactionDTO> productWithReaction;

    public RoutineWithReactionDTO() {
    }

    public RoutineWithReactionDTO(Routine routine) {
        this.startDate = routine.getStartDate();
        this.productWithReaction = routine.getProducts().stream().map(ProductReactionDTO::new).collect(Collectors.toList());

    }

    public RoutineWithReactionDTO(LocalDate startDate, List<ProductReactionDTO> productWithReaction) {
        this.startDate = startDate;
        this.productWithReaction = productWithReaction;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<ProductReactionDTO> getProductWithReaction() {
        return productWithReaction;
    }

    public void setProductWithReaction(List<ProductReactionDTO> productWithReaction) {
        this.productWithReaction = productWithReaction;
    }
}
