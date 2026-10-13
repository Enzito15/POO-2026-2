module pe.edu.upeu.proyect_final {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    //requiero la dependencia del sqlite
    requires org.xerial.sqlitejdbc;
    requires javafx.base;
    requires pe.edu.upeu.proyect_final;
    requires javafx.graphics;

    opens pe.edu.upeu.proyect_final to javafx.fxml;
    //Abrir el modelo para que las tablas de FX Funcionen
    opens pe.edu.upeu.proyect_final.modelo to javafx.base,javafx.fxml;
    opens pe.edu.upeu.proyect_final.controller to javafx.fxml;
    opens pe.edu.upeu.proyect_final.view to javafx.fxml;


    exports pe.edu.upeu.proyect_final;

    exports pe.edu.upeu.proyect_final.utils;
    exports pe.edu.upeu.proyect_final.test;
    exports pe.edu.upeu.proyect_final.interfaces;
    exports pe.edu.upeu.proyect_final.implementacion;
    exports pe.edu.upeu.proyect_final.controller;

}