/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Bukutelpon;
import entities.Detail;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.detailIM;
import org.hibernate.annotations.Source;

/**
 * FXML Controller class
 *
 * @author Minami
 */
public class DetailController implements Initializable {

    @FXML
    private Button bsimpan, bhapus, bupdate, bform2;
    @FXML
    private TextField tidbuku, talamat, tcari;
    @FXML
    private TableView tv;
    @FXML
    private TableColumn<Detail, String> id, nama, nohp, idbuku, alamat;
    String key;
    detailIM m = new detailIM();
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
    }

    private void baca() {
        tv.getItems().clear();
        List<Bukutelpon> ls = m.baca();
        for (Bukutelpon l : ls) {
            //System.out.println(l.getId()+"-"+l.getNama()+"-"+l.getDetail().getAlamat());
            //ols.add(l);
            l.setId(l.getId());
            l.setNama(l.getNama());
            l.setDetail(l.getDetail());
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        nohp.setCellValueFactory(new PropertyValueFactory<>("nohp"));
        idbuku.setCellValueFactory(new PropertyValueFactory<>("idBuku"));
        alamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        tv.setItems(ols);

    }

    private void simpan() {
        bsimpan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Detail b = new Detail();
                b.setIdBuku(Integer.parseInt(tidbuku.getText()));
                b.setAlamat(talamat.getText());
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
                Detail b = new Detail();
                b.setId(Integer.parseInt(key));
                b.setIdBuku(Integer.parseInt(tidbuku.getText()));
                b.setAlamat(talamat.getText());
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
                Detail b = new Detail();
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
                idbuku.setCellValueFactory(new PropertyValueFactory<>("idbuku"));
                alamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
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
                tidbuku.setText(idbuku.getCellData(i));
                talamat.setText(alamat.getCellData(i));
            }
        });
    }

}
