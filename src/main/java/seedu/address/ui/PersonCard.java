package seedu.address.ui;

import static seedu.address.ui.TagCategories.isIndustry;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label pinIndicator;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox events;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);

        if (person.isPinned()) {
            pinIndicator.setVisible(true);
        } else {
            pinIndicator.setVisible(false);
        }

        person.getTags().stream()
                .sorted(Comparator.comparing((Tag t) -> !isIndustry(t.tagName))
                        .thenComparing(t -> t.tagName.toLowerCase()))
                .forEach(tag -> {
                    Label tagLabel = new Label(tag.tagName);
                    tagLabel.getStyleClass().add(isIndustry(tag.tagName) ? "tag-industry" : "tag-default");
                    tags.getChildren().add(tagLabel);
                });
        person.getEvents().stream()
                .forEach(event -> events.getChildren().add(new Label(event.toString())));
    }
}
