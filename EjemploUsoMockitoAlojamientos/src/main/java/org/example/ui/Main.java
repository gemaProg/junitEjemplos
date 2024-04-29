package org.example.ui;


import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Log4j2
public class Main {
    //este atributo no hace falta si utilizamos la anotación
    //private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        log.error("App arrancada");
        MainAlojamientos main = new MainAlojamientos();
        main.main();
        log.error("App finalizada");
    }
}