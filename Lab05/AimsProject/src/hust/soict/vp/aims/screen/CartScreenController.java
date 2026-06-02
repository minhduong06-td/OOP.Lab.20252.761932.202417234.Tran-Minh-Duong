package hust.soict.vp.aims.screen;

import hust.soict.vp.aims.cart.Cart;
import hust.soict.vp.aims.exception.PlayerException;
import hust.soict.vp.aims.media.Media;
import hust.soict.vp.aims.media.Playable;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartScreenController {
    private final Cart cart;
    private FilteredList<Media> filteredItems;

    @FXML
    private TableView<Media> tblMedia;
    @FXML
    private TableColumn<Media, String> colMediaTitle;
    @FXML
    private TableColumn<Media, String> colMediaCategory;
    @FXML
    private TableColumn<Media, Float> colMediaCost;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnRemove;
    @FXML
    private TextField tfFilter;
    @FXML
    private RadioButton radioBtnFilterId;
    @FXML
    private RadioButton radioBtnFilterTitle;
    @FXML
    private Label labelTotalCost;
    @FXML
    private Button btnPlaceOrder;

    public CartScreenController(Cart cart) {
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));

        filteredItems = new FilteredList<Media>(cart.getItemsOrdered(), media -> true);
        tblMedia.setItems(filteredItems);

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
        updateTotalCost();

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateButtonBar(newValue));
        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> applyFilter());
    }

    @FXML
    private void btnPlayPressed() {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (!(media instanceof Playable)) {
            return;
        }

        try {
            ((Playable) media).play();
        } catch (PlayerException e) {
            showAlert(Alert.AlertType.ERROR, "Player error", e.getMessage());
        }
    }

    @FXML
    private void btnRemovePressed() {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
            tblMedia.getSelectionModel().clearSelection();
            updateTotalCost();
        }
    }

    @FXML
    private void btnPlaceOrderPressed() {
        if (cart.getItemsOrdered().isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Cart", "Cart is empty.");
            return;
        }

        cart.clear();
        updateTotalCost();
        showAlert(Alert.AlertType.INFORMATION, "Cart", "Order placed.");
    }

    private void updateButtonBar(Media media) {
        btnRemove.setVisible(media != null);
        btnPlay.setVisible(media instanceof Playable);
    }

    private void updateTotalCost() {
        labelTotalCost.setText(String.format("%.2f $", cart.totalCost()));
    }

    private void applyFilter() {
        String keyword = tfFilter.getText();
        if (keyword == null || keyword.trim().isEmpty()) {
            filteredItems.setPredicate(media -> true);
            return;
        }

        String normalized = keyword.trim().toLowerCase();
        filteredItems.setPredicate(media -> {
            if (radioBtnFilterId.isSelected()) {
                return String.valueOf(media.getId()).contains(normalized);
            }
            return media.getTitle() != null
                    && media.getTitle().toLowerCase().contains(normalized);
        });
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
