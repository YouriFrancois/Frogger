package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //=================================================================
        Rectangle r = new Rectangle(300,320,40,40);
        r.setFill(Color.RED);

        //==========================================================================
        int sc=0,hp=4;
        Label lab= new Label(" SCORE  "+score());
        //Label lab1= new Label(" hp    "+hp);
        HBox hb1= new HBox(50);
        hb1.setPadding(new Insets(5,5,5,5));
        hb1.setAlignment(Pos.TOP_CENTER);
        //==================================================================
        Pane root = new Pane();
        Pane root1 = new Pane();
        //========================================================================
        Button btn4 = new Button(" right ");
        //===================================================================
        HBox vbox= new HBox(10);
        vbox.setPadding(new Insets(400,5,5,5));
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.getChildren().addAll(btn4);

        //========================================================================
        // 1st line
        Rectangle r1 = new Rectangle(860,50,750,40);
        Rectangle r2 = new Rectangle(0,50,750,40);

        //=======================================================================
        // 2nd line
        Rectangle r3 = new Rectangle(0,160,600,40);
        Rectangle r4 = new Rectangle(700,160,800,40);

        //========================================================================
        //3rd line
        Rectangle r5 = new Rectangle(40,270,800,40);
        Rectangle r6 = new Rectangle(950,270,800,40);


        //===============================================================================================================
        String str= new String();
        hp=vhp();
        Label lab1= new Label(" hp "+hp);

        move(root1, r);
        touch(r,r1,r2,r3,r4,r5,r6);

        //-------------------------------------------------------------------------------
        hb1.getChildren().addAll(lab,lab1);
        root.getChildren().addAll(r1,r2,r3,r4,r5,r6);
        root1.getChildren().addAll(r,vbox,hb1,root);

        //=================================================================
        TranslateTransition t4 = new TranslateTransition();
        t4.setDuration(Duration.millis(13000));
        t4.setNode(root);
        t4.setCycleCount(50);
        t4.setToX(-900);
        t4.play();

        //=====================================================================================
        if (vhp()==0){t4.pause();}

        Scene scene = new Scene(root1, 600, 400);
        primaryStage.setTitle(" my game ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//************************************************************************
    public static void main(String[] args) {
        launch(args);
    }
    //**********************************************************************
    int m=320,n=60,x=0, hp=4;

    public int moveleft(){
        if (n<40){n=40;}
        return n-=20;}

    public int moveright(){
        if (n>520){n=520;}
        return n+=20;}

    public int moveup(){
        if (m<0){m=360;score();}
        return m-=20;}

    public int score(){
        return x+5; }

    public void touch(Rectangle r,Rectangle r1,Rectangle r2,Rectangle r3,Rectangle r4,Rectangle r5,Rectangle r6){
        r.boundsInParentProperty().addListener((ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) -> {
            if( r1.localToScene(r1.getBoundsInLocal()).intersects(r.localToScene(r.getBoundsInLocal()))){
                r.setFill(Color.YELLOW);vhp();}

            if( r2.localToScene(r2.getBoundsInLocal()).intersects(r.localToScene(r.getBoundsInLocal()))){
                r.setFill(Color.PINK);vhp();}

            if( r3.localToScene(r3.getBoundsInLocal()).intersects(r.localToScene(r.getBoundsInLocal()))){
                r.setFill(Color.BLACK);vhp();}

            if( r4.localToScene(r4.getBoundsInLocal()).intersects(r.localToScene(r.getBoundsInLocal()))){
                r.setFill(Color.RED);vhp();}

            if( r5.localToScene(r5.getBoundsInLocal()).intersects(r.localToScene(r.getBoundsInLocal()))){
                r.setFill(Color.GREEN);vhp();}

            if( r6.localToScene(r6.getBoundsInLocal()).intersects(r.localToScene(r.getBoundsInLocal()))){
                r.setFill(Color.BLUE);vhp();}
        });}

    public void move(Pane root1,Rectangle r){
        root1.setOnKeyPressed(e->{
            switch(e.getCode()){
                case W:r.setY(moveup());break;
                case A:r.setX(moveleft()) ;break;
                case D:r.setX(moveright());break;
                default:System.out.println(" no key "); }
        });
    }

    public void chp(){
        hp-=1;}

    public int vhp(){
        System.out.println("hp is"+hp);
        return hp-=1;}


}
