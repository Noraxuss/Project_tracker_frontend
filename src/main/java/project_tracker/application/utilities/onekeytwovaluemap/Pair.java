package project_tracker.application.utilities.onekeytwovaluemap;

import lombok.Getter;

@Getter
public class Pair<V1, V2> {
    private final V1 value1;
    private final V2 value2;

    public Pair(V1 value1, V2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

}
