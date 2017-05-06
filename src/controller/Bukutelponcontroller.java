/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Bukutelpon;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.bukutelponIM;

/**
 *
 * @author Minami
 */
public class Bukutelponcontroller implements Initializable {

    @FXML
    private Button bsimpan, bhapus, bupdate, bform2;
    @FXML
    private TextField tnama, tnohp, tcari;
    @FXML
    private TableView tv;
    @FXML
    private TableColumn<Bukutelpon, String> id, nama, nohp;
    String key;
    bukutelponIM m = new bukutelponIM();
    ObservableList ols = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        baca();
        simpan();
        hapus();
        update();
        cari();
        selectrow();
        newform();
    }

    private void baca() {
        tv.getItems().clear();
        List<Bukutelpon> ls = m.baca();
        for (Bukutelpon l : ls) {
            ols.add(l);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        nohp.setCellValueFactory(new PropertyValueFactory<>("nohp"));
        tv.setItems(ols);

    }

    private void simpan() {
        bsimpan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Bukutelpon b = new Bukutelpon();
                b.setNama(tnama.getText());
                b.setNohp(tnohp.getText());
                m.simpan(b);
                baca();
            }
        });
    }

    private void update() {
        bupdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Bukutelpon b = new Bukutelpon();
                b.setId(Integer.parseInt(key));
                b.setNama(tnama.getText());
                b.setNohp(tnohp.getText());
                m.update(b);
                baca();
            }
        });
    }

    private void hapus() {
        bhapus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Bukutelpon b = new Bukutelpon();
                b.setId(Integer.parseInt(key));
                m.hapus(b);
                baca();
            }
        });
    }

    private void cari() {

        tcari.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                tv.getItems().clear();
                List<Bukutelpon> ls = m.cari(tcari.getText());
                for (Bukutelpon l : ls) {
                    ols.add(l);
                }

                id.setCellValueFactory(new PropertyValueFactory<>("id"));
                nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
                nohp.setCellValueFactory(new PropertyValueFactory<>("nohp"));
                tv.setItems(ols);
            }
        });

    }

    public void selectrow() {
        tv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int i = tv.getSelectionModel().getSelectedIndex();
                key = String.valueOf(id.getCellData(i));
                tnama.setText(nama.getCellData(i));
                tnohp.setText(nohp.getCellData(i));
            }
        });
    }

    public void newform() {
        bform2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    Stage stage=new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/view/detail.fxml"));
                    
                    Scene scene = new Scene(root);
                    
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(Bukutelponcontroller.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

}
