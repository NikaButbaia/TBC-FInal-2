package ge.tbc.testautomation.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SectionComponent {

    private String type;
    private String key;
    private SectionInputs inputs;

    public List<ListItem> getItems() {
        return inputs != null ? inputs.getList() : null;
    }
}