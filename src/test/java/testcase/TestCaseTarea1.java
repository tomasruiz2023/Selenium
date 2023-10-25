package testcase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.charset.StandardCharsets;

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
        driver.get("https://sitio.consorcio.cl/");

        //maximizar el browser
        driver.manage().window().maximize();
    }
    @AfterEach
    public void posCondiciones(){
        //pos condicion
      //  driver.quit();
    }

    @Test
    void ingresoClienteExistente() throws InterruptedException {

        WebElement btnPortate = driver.findElement(By.id("hi_header_login_a"));
        WebElement btnHazteCliente = driver.findElement(By.id("header_hazte_cliente_banco"));

        btnPortate.click();
        if (btnHazteCliente.isDisplayed())
            btnHazteCliente.click();

        WebElement txtIngresoRut = driver.findElement(By.name("rut"));
        txtIngresoRut.sendKeys("12646609-9");
        Thread.sleep(2000); //ESPERA 3 SEGUNDOS
        driver.findElement(By.id("nacionalidadChileno")).click();
        driver.findElement(By.id("otraNacionalidadNo" )).click();
        driver.findElement(By.name("email")).sendKeys("t@mail.cl");
        driver.findElement(By.name("celular")).sendKeys("999999999");
        driver.findElement(By.xpath("//cns-button[@label='Continuar']")).click();
        Thread.sleep(2000); //ESPERA 3 SEGUNDOS


        WebElement btnIrConsorcio = driver.findElement(By.xpath("//cns-button[@label='Ir a Consorcio.cl']"));
        String resultadoActual = corregirFormatoTexto(driver.findElement(By.className("title")).getText());
        String resultadoEsperado = "Ya tienes una cuenta vista vigente";

         if(btnIrConsorcio.isDisplayed()){
            // System.out.println("existe boton");
             btnIrConsorcio.click();

         }


        Assertions.assertEquals(resultadoEsperado,resultadoActual);

    }



    @Test
    void traeDatosRutNuevo() throws InterruptedException {

        WebElement btnPortate = driver.findElement(By.id("hi_header_login_a"));
        WebElement btnHazteCliente = driver.findElement(By.id("header_hazte_cliente_banco"));

        btnPortate.click();
        if (btnHazteCliente.isDisplayed())
            btnHazteCliente.click();

        WebElement txtIngresoRut = driver.findElement(By.name("rut"));
        txtIngresoRut.sendKeys("12646609-9");
        Thread.sleep(2000); //ESPERA 3 SEGUNDOS
        driver.findElement(By.id("nacionalidadChileno")).click();
        driver.findElement(By.id("otraNacionalidadNo" )).click();
        driver.findElement(By.name("email")).sendKeys("t@mail.cl");
        driver.findElement(By.name("celular")).sendKeys("999999999");
        driver.findElement(By.xpath("//cns-button[@label='Continuar']")).click();
        Thread.sleep(2000); //ESPERA 3 SEGUNDOS


        WebElement btnIrConsorcio = driver.findElement(By.xpath("//cns-button[@label='Ir a Consorcio.cl']"));
        String resultadoActual = corregirFormatoTexto(driver.findElement(By.className("title")).getText());
        String resultadoEsperado = "Ya tienes una cuenta vista vigente";

        if(btnIrConsorcio.isDisplayed()){
            // System.out.println("existe boton");
            btnIrConsorcio.click();

        }


        Assertions.assertEquals(resultadoEsperado,resultadoActual);

    }










    public static String corregirFormatoTexto(String textoIncorrecto){
        byte[] bytes = textoIncorrecto.getBytes(StandardCharsets.UTF_8);
        String textoCorrejido = new String(bytes, StandardCharsets.ISO_8859_1);

        return textoCorrejido;
    }
}
