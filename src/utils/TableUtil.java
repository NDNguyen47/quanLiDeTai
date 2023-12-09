package utils;

import java.util.concurrent.atomic.AtomicLong;

import javafx.application.Platform;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class TableUtil
{
    public static void autoResizeColumns(TableView<?> table)
    {
        table.setColumnResizePolicy((param) -> true );
        Platform.runLater(() -> customResize(table));

        
    }

    public static void customResize(TableView<?> view) {

        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth()+((tableWidth-width.get())/view.getColumns().size()));
            });
        }
    }
}
