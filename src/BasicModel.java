import java.util.List;
import java.util.Set;

public class BasicModel implements Model {

    private Set<Component> components;

    private List<Integer> samples;

    public BasicModel() { }

    public BasicModel(Set<Component> components, List<Integer> samples) {
        this.components = components;
        this.samples = samples;
    }


    public Set<Component> getComponents() {
        return components;
    }

    public void setComponents(Set<Component> components) {
        this.components = components;
    }

    public List<Integer> getSamples() {
        return samples;
    }

    public void setSamples(List<Integer> samples) {
        this.samples = samples;
    }

    public int getSampleSize() {
        return samples.size();
    }

    @Override
    public double getRelativeSampleLikelihood(int sample, Component component) {

        double t = 0;
        for (Component c : getComponents()) {
            t += c.getSampleLikelihood(sample) * c.getTau();
        }

        double l = component.getSampleLikelihood(sample) * component.getTau() / t;
        if (Double.isNaN(l)) {
            return 0;
        }

        return l;
    }

    @Override
    public double getLikelihood() {
        double l = 0;
        for (int sample : getSamples()) {
            for (Component component : getComponents()) {
                l += Math.log(component.getSampleLikelihood(sample));
            }
        }

        return l / getSampleSize();
    }
}
