package com.example.papcreativaexpress.Controllers;

import com.example.papcreativaexpress.Model.Lote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InventarioController {

    @FXML
    private Button btnEmpleados;

    @FXML
    private Button btnProductos;

    @FXML
    private Button btnProveedores;

    @FXML
    private Button btnVerInfo;

    @FXML
    private TableColumn<Lote, Integer> colCantidadProductos;

    @FXML
    private TableColumn<Lote, String> colMarca;

    @FXML
    private TableColumn<Lote, String> colProductos;

    @FXML
    private TableColumn<Lote, String> colProveedor;

    @FXML
    private TableView<Lote> tblPoductos;

    @FXML
    private TextField tfBuscarProducto;

    @FXML
    void OnActionEmpleados(ActionEvent event) {

    }

    @FXML
    void OnActionProductos(ActionEvent event) {

    }

    @FXML
    void OnActionProveedores(ActionEvent event) {

    }

    @FXML
    void OnActionVerInfo(ActionEvent event) {

    }

}


