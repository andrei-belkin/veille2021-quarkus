package qc.ca.claurendeau.belkinandrei.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewState {
    PENDING("en attente"),
    APPROVED("approuvé"),
    DENIED("refusé");

    private final String value;
}
