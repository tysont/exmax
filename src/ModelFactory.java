import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ModelFactory {

    public static Model maximize(Model model) {
        for (int i = 0; i < 100; i++) {

            Set<Component> modelComponents = model.getComponents();
            List<Integer> modelSamples = model.getSamples();
            Set<Component> nextModelComponents = new HashSet<Component>();

            for (Component component : modelComponents) {

                double t = 0;
                for (int sample : modelSamples) {
                    t += model.getRelativeSampleLikelihood(sample, component);
                }

                double mu = 0;
                for (int sample : modelSamples) {
                    mu += sample * model.getRelativeSampleLikelihood(sample, component) / t;
                }

                double s = 0;
                double sigma = 0;
                for (int sample : modelSamples) {
                    double d = model.getRelativeSampleLikelihood(sample, component);
                    sigma += Math.pow(sample - mu, 2) * d;
                    s += d;
                }

                sigma = (component.getSigma() + (sigma / s)) / 2;
                nextModelComponents.add(new BasicComponent(mu, sigma, component.getTau()));
            }

            model = new BasicModel(nextModelComponents, modelSamples);
            System.out.println(model.getLikelihood());
        }

        return model;
    }

}
