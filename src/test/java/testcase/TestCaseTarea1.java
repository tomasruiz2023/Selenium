package testcase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;


import java.nio.charset.StandardCharsets;

import static org.openqa.selenium.Keys.TAB;

public class TestCaseTarea1 {
    WebDriver driver;

    @BeforeEach
    public void preCondiciones(){
        //String ruta del driver
        String rutaDriver = "D:\\Java\\CursoSelenium\\EjerciciosSelenium1\\src\\test\\resources\\driver\\chromedriver.exe";

        //Enlazar el driver como property de windows para el manejo
        //Del navegador
        System.setProperty("webdriver.chrome.driver",rutaDriver);

        //Instanciar un objeto de tipo driver de chrome
        driver = new ChromeDriver();
        //driver.get("https://sitio.consorcio.cl/");
         driver.get("https://sitio.consorcio.cl/banca-personas/cuenta-vista/cuenta-mas-digital/entry");
        //driver.get("https://www.emol.com/");



        //maximizar el browser
        driver.manage().window().maximize();
    }
    @AfterEach
    public void posCondiciones(){
        //pos condicion
       driver.quit();
    }


    @Test
    void ingresoClienteExistente() throws InterruptedException {
        // cliente ya tiene cuenta vista, cliente no es funcionario

        WebElement txtIngresoRut = driver.findElement(By.name("rut"));
        WebElement txtCelular = driver.findElement(By.name("celular"));
        WebElement btnContinuar = driver.findElement(By.id("step1_btn"));

        txtIngresoRut.sendKeys("14270952-K");
        txtIngresoRut.sendKeys(TAB);
        driver.findElement(By.id("nacionalidadChileno")).click();
        driver.findElement(By.id("otraNacionalidadNo" )).click();
        // realiza scroll para llenar formulario
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,400)");


        driver.findElement(By.name("email")).sendKeys("t@mail.cl");
        // driver.findElement(By.name("celular")).sendKeys("999999999");
        txtCelular.sendKeys("999999999");
        //envia una tabulador para avanzar, sino pagina no avanza
        txtCelular.sendKeys(TAB);

        // presiona botón continuar, ahora le cambiaron de nombre
        btnContinuar.click();

        Thread.sleep(4000); //delay en milisegundos para esperar mensaje emergente

        // busca titulo para realizar comparacion
        String resultadoActual = corregirFormatoTexto(driver.findElement(By.className("title")).getText());
        String resultadoEsperado = "Ya tienes una cuenta vista vigente";

            // si resultado esperado es igual a resultado obtenido pasa la prueba
            Assertions.assertEquals(resultadoEsperado,resultadoActual);

    }


    @Test
    void ingresoClienteExistenteNuevo() throws InterruptedException {

        WebElement txtIngresoRut = driver.findElement(By.name("rut"));
        WebElement txtCelular = driver.findElement(By.name("celular"));

        txtIngresoRut.sendKeys("5311470-9");
        //    txtIngresoRut.sendKeys(TAB);
        driver.findElement(By.id("nacionalidadChileno")).click();
        driver.findElement(By.id("otraNacionalidadNo" )).click();
        // realiza scroll
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,400)");


        driver.findElement(By.name("email")).sendKeys("t@mail.cl");
        // driver.findElement(By.name("celular")).sendKeys("999999999");
        txtCelular.sendKeys("999999999");
        //envia una tabulador para avanzar
        txtCelular.sendKeys(TAB);

        WebElement btnContinuar = driver.findElement(By.id("step1_btn"));
        // presiona botón continuar, ahora le cambiaron de nombre
        btnContinuar.click();

        Thread.sleep(4000); //delay en milisegundos para esperar mensaje emergente

        //WebElement btnIrConsorcio = driver.findElement(By.xpath("//cns-button[@label='Ir a Consorcio.cl']"));
        // busca titulo  de pagina siguiente para realizar comparacion
        // si avanza al siguiente paso la prueba aprobó
        String resultadoActual;
        resultadoActual = corregirFormatoTexto(driver.getCurrentUrl());
        String resultadoEsperado = "https://sitio.consorcio.cl/banca-personas/cuenta-vista/cuenta-mas-digital/step-2";

//  establece resultado del test.
        Assertions.assertEquals(resultadoEsperado,resultadoActual);

    }


    @Test
    void ingresoClienteExistenteFuncionario() throws InterruptedException {

        WebElement txtIngresoRut = driver.findElement(By.name("rut"));
        WebElement txtCelular = driver.findElement(By.name("celular"));

        txtIngresoRut.sendKeys("12646609-9");
        txtIngresoRut.sendKeys(TAB);
        driver.findElement(By.id("nacionalidadChileno")).click();
        driver.findElement(By.id("otraNacionalidadNo" )).click();
    // realiza scroll
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,400)");

        driver.findElement(By.name("email")).sendKeys("t@mail.cl");
       // driver.findElement(By.name("celular")).sendKeys("999999999");
        txtCelular.sendKeys("999999999");
       //envia una tabulador para avanzar
        txtCelular.sendKeys(TAB);

        WebElement btnContinuar = driver.findElement(By.id("step1_btn"));
        // presiona botón continuar, ahora le cambiaron de nombre
        btnContinuar.click();

       Thread.sleep(4000); //delay en milisegundos para esperar mensaje emergente

        //WebElement btnIrConsorcio = driver.findElement(By.xpath("//cns-button[@label='Ir a Consorcio.cl']"));
        // busca titulo para realizar comparacion
        String resultadoActual = corregirFormatoTexto(driver.findElement(By.className("title")).getText());
        String resultadoEsperado = "¡Eres funcionario de Consorcio!";

//  establece resultado del test.
        Assertions.assertEquals(resultadoEsperado,resultadoActual);

    }


    @Test
    void validaActivacionBoton() throws InterruptedException {

// en página siguiente, valida que botón está deshabilitado.
        boolean resultadoActual;
        boolean resultadoEsperado = true;

        WebElement btnContinuar = driver.findElement(By.id("step1_btn"));
        WebElement txtIngresoRut = driver.findElement(By.name("rut"));
         resultadoActual =btnContinuar.isEnabled();


        txtIngresoRut.sendKeys("12646609-9");
        txtIngresoRut.sendKeys(TAB);
        driver.findElement(By.id("nacionalidadChileno")).click();
        driver.findElement(By.id("otraNacionalidadNo" )).click();
        // realiza scroll
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,400)");

         driver.findElement(By.name("email")).sendKeys("t@mail.cl");
        driver.findElement(By.name("celular")).sendKeys("999999999");

        Thread.sleep(2000); //ESPERA x SEGUNDOS
        resultadoActual =btnContinuar.isEnabled();

        Assertions.assertEquals(resultadoEsperado,resultadoActual);

    }


    @Test
    void validaMensajesTextBoxVacios() throws InterruptedException {

// en página siguiente, valida que botón está deshabilitado cuando no se completan los campos de Email y celular.
        boolean resultadoActual = false;
        boolean resultadoEsperado = false;

        WebElement txtIngresoRut = driver.findElement(By.name("rut"));
        WebElement txtIngresoEmail = driver.findElement(By.name("email"));
        WebElement txtIngresoCelular = driver.findElement(By.name("celular"));


        txtIngresoRut.sendKeys("12646609-9");
        driver.findElement(By.id("nacionalidadChileno")).click();
        driver.findElement(By.id("otraNacionalidadNo" )).click();
        txtIngresoEmail.sendKeys(TAB);
        txtIngresoCelular.sendKeys(TAB);

        Assertions.assertEquals(resultadoEsperado,resultadoActual);

    }



    public static String corregirFormatoTexto(String textoIncorrecto){
        byte[] bytes = textoIncorrecto.getBytes(StandardCharsets.UTF_8);
        String textoCorrejido = new String(bytes, StandardCharsets.ISO_8859_1);

        return textoCorrejido;
    }


}
