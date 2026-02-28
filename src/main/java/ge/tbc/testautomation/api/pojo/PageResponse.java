package ge.tbc.testautomation.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageResponse {

    private String slug;
    private String locale;
    private String pageType;
    private String key;

    @JsonProperty("sectionComponents")
    private List<SectionComponent> sections;

    public String getTitle() {
        if (sections != null) {
            return sections.stream()
                    .filter(s -> s.getInputs() != null && s.getInputs().getTitle() != null)
                    .map(s -> s.getInputs().getTitle())
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
}