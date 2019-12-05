import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Media Player");

        // Create media player
        File file = new File("test.mp4");
        File srt = new File("test.srt");
        Media media = new Media("file:///" + file.getAbsolutePath());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        Button playButton = new Button("\u23F8");

        // setup the subtitle thread
        Label subtitleMeaning = new Label("hi");
        SubtitleParser subtitleParser = null;
        HBox subtitles = new HBox(0);
        try {
            subtitleParser = new SubtitleParser(srt);
            while (subtitleParser.hasNext()){
                Subtitle subtitle = subtitleParser.next();
                Timeline setSubtitles = new Timeline(new KeyFrame(Duration.millis(subtitle.getFromMillis()), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        subtitles.getChildren().clear();
                        String[] a = subtitle.getText().split(" ");
                        for (String s : a) {
                            Button b = new Button(s);
                            b.setStyle("-fx-background-color: #ff0000; -fx-border-color: #ff0000; -fx-border-width: 5px;");
                            b.setOnAction(ev -> {
                                mediaPlayer.pause();
                                playButton.setText("\u25B6");
                                String url = "https://dictionaryapi.com/api/v3/references/collegiate/json/"+ b.getText().replaceAll("[^a-zA-Z0-9]", "").toLowerCase()+"?key=2445ff20-cd81-46b1-a5e5-555497d3e8f5";
                                //
                                try {
                                    URL obj = new URL(url);
                                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                                    con.setRequestMethod("GET");
                                    con.setRequestProperty("User-Agent", "UwU");
                                    int responseCode = con.getResponseCode();
                                    System.out.println("GET Response Code :: " + responseCode);
                                    if (responseCode == HttpURLConnection.HTTP_OK) {
                                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                                        String inputLine;
                                        StringBuffer response = new StringBuffer();

                                        while ((inputLine = in.readLine()) != null) {
                                            response.append(inputLine);
                                        }
                                        in.close();

                                        // print result
                                        System.out.println(response.toString());
                                    } else {
                                        subtitleMeaning.setText("failed to connect to server.");
                                    }
                                } catch (Exception exc) {
                                    exc.printStackTrace();
                                }
                                //
                                System.out.println(b.getText());
                            });
                            subtitles.getChildren().add(b);
                        }
                    }
                }));
                setSubtitles.play();
                Timeline removeSubtitles = new Timeline(new KeyFrame(Duration.millis(subtitle.getToMillis()), event -> subtitles.getChildren().clear()));
                removeSubtitles.play();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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


        //Control panel
        HBox controls = new HBox(8);
        controls.getChildren().add(playButton);
        controls.getChildren().add(subtitles);

        VBox mainLayout = new VBox(8);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(800);
        mediaView.setFitHeight(500);
        mainLayout.getChildren().add(mediaView);
        mainLayout.getChildren().add(controls);
        mainLayout.getChildren().add(subtitleMeaning);

        // Add media display node to the scene graph
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
