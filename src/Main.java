import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Media Player");

        // Create media player
        File file = new File("test.mp4");
        Media media = new Media("file:///" + file.getAbsolutePath());
        javafx.scene.media.MediaPlayer mediaPlayer = new javafx.scene.media.MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        Button playButton = new Button("\u23F8");
//        Slider seekBar = new Slider(0, media.getDuration().toSeconds(), 0);
//        seekBar.setBlockIncrement(1);
//        seekBar.setShowTickMarks(true);
//        seekBar.setShowTickLabels(true);

        // Create the Event Handlers for the Button
        playButton.setOnAction(event -> {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING){
                mediaPlayer.pause();
                playButton.setText("\u25B6");
            } else {
                mediaPlayer.play();
                playButton.setText("\u23F8");
            }
        });

//        Timer timer = new Timer(1000, e -> {
//            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) seekBar.increment();
//        });
//        seekBar.setOnMouseReleased(event -> {
//            mediaPlayer.seek(new Duration(seekBar.getValue()*1000));
//            System.out.println(seekBar.getValue());
//        });
        //Control panel
        HBox controls = new HBox(8);
        controls.getChildren().add(playButton);
//        controls.getChildren().add(seekBar);

        VBox mainLayout = new VBox(8);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(800);
        mediaView.setFitHeight(500);
        mainLayout.getChildren().add(mediaView);
        mainLayout.getChildren().add(controls);

        // Add media display node to the scene graph
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
//        timer.start();
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}