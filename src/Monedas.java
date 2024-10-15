import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class Monedas {

        @SerializedName("base_code")
        public String baseCode;  // Moneda base

        @SerializedName("conversion_rates")
        public Map<String, Double> conversionRates;  // Mapa de tasas de conversión

        @Override
        public String toString() {
                return "Monedas{" +
                        "Moneda base='" + baseCode + '\'' +
                        ", Tasas de conversión=" + conversionRates +
                        '}';
        }
}
