package corn_snake.facade.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.lang.reflect.Field;

public class FieldController {
    @FXML
    private ImageView A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15;
    @FXML
    private ImageView B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13, B14, B15;
    @FXML
    private ImageView C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15;
    @FXML
    private ImageView D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D13, D14, D15;
    @FXML
    private ImageView E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15;
    @FXML
    private ImageView F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15;
    @FXML
    private ImageView G1, G2, G3, G4, G5, G6, G7, G8, G9, G10, G11, G12, G13, G14, G15;
    @FXML
    private ImageView H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13, H14, H15;
    @FXML
    private ImageView I1, I2, I3, I4, I5, I6, I7, I8, I9, I10, I11, I12, I13, I14, I15;
    @FXML
    private ImageView J1, J2, J3, J4, J5, J6, J7, J8, J9, J10, J11, J12, J13, J14, J15;
    @FXML
    private ImageView K1, K2, K3, K4, K5, K6, K7, K8, K9, K10, K11, K12, K13, K14, K15;
    @FXML
    private ImageView L1, L2, L3, L4, L5, L6, L7, L8, L9, L10, L11, L12, L13, L14, L15;
    @FXML
    private ImageView M1, M2, M3, M4, M5, M6, M7, M8, M9, M10, M11, M12, M13, M14, M15;
    @FXML
    private ImageView N1, N2, N3, N4, N5, N6, N7, N8, N9, N10, N11, N12, N13, N14, N15;
    @FXML
    private ImageView O1, O2, O3, O4, O5, O6, O7, O8, O9, O10, O11, O12, O13, O14, O15;

    @FXML
    private Label score;

    @FXML
    private ImageView gameOver;

    private final static Image placeholder = new Image(
            "file:///" + FieldController.class.getResource("Tile.png").getFile()
    );

    public void updateFrame(ActionEvent e) throws IllegalAccessException {
        final int BORDER = 15;

        Field[] fields = getClass().getDeclaredFields();

        int row = 0;
        int column = 0;

        for (Field field : fields) {
            Class<?> type = field.getType();
            Object value = field.get(this);

            if (type != ImageView.class) {
                continue;
            } else {
                ImageView image = (ImageView) value;
                image.setImage(placeholder);
                column++;
            }

            if (column > BORDER) {
                row++;
                column = 0;
            }

            if (row > BORDER) {
                return;
            }
        }
    }
}
