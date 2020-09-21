package review.config.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/9/21 15:45
 */
@ToString
@Getter
@Setter
public class Gamer {

    private String id;

    private String name;

    private double score;

    public Gamer() {
    }

    public Gamer(String id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Gamer gamer = (Gamer) o;
        return Double.compare(gamer.score, score) == 0 &&
                Objects.equals(id, gamer.id) &&
                Objects.equals(name, gamer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, score);
    }
}
