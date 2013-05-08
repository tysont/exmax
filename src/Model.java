import java.util.List;
import java.util.Set;


public interface Model {

    Set<Component> getComponents();

    void setComponents(Set<Component> components);

    List<Integer> getSamples();

    void setSamples(List<Integer> samples);

    double getRelativeSampleLikelihood(int sample, Component component);

    double getLikelihood();
}
