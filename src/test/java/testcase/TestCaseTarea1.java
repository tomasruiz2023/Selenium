package testcase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.security.x509.OtherName;

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
    void validaActivacionBoton() throws InterruptedException {
// ingreso a pagina principal de sitio consorcio
        WebElement btnPortate = driver.findElement(By.id("hi_header_login_a"));
        WebElement btnHazteCliente = driver.findElement(By.id("header_hazte_cliente_banco"));

                btnPortate.click();
        if (btnHazteCliente.isDisplayed())
            btnHazteCliente.click();
// en página siguiente, valida que botón está deshabilitado.
        boolean resultadoActual = false;
        boolean resultadoEsperado = false;

        WebElement btnContinuar = driver.findElement(By.tagName("cns-button"));
        WebElement txtIngresoRut = driver.findElement(By.name("rut"));
        resultadoActual =btnContinuar.isEnabled();
        if (!btnContinuar.isEnabled()) {  txtIngresoRut.sendKeys("12646609-9"); } else  {resultadoActual= true; }
        if (!btnContinuar.isEnabled()) {  driver.findElement(By.id("nacionalidadChileno")).click();} else  {resultadoActual= true; }
        if (!btnContinuar.isEnabled()) {   driver.findElement(By.id("otraNacionalidadNo" )).click();} else  {resultadoActual= true; }
        if (!btnContinuar.isEnabled()) {driver.findElement(By.name("email")).sendKeys("t@mail.cl");} else  {resultadoActual= true; }
        if (!btnContinuar.isEnabled()) {driver.findElement(By.name("celular")).sendKeys("999999999");} else  {resultadoActual= true; }

        resultadoActual =btnContinuar.isEnabled();

        Assertions.assertEquals(resultadoEsperado,resultadoActual);

    }

    @Test
    void validaMensajesTextBoxVacios() throws InterruptedException {
// ingreso a pagina principal de sitio consorcio
        WebElement btnPortate = driver.findElement(By.id("hi_header_login_a"));
        WebElement btnHazteCliente = driver.findElement(By.id("header_hazte_cliente_banco"));

        btnPortate.click();
        if (btnHazteCliente.isDisplayed())
            btnHazteCliente.click();
// en página siguiente, valida que botón está deshabilitado.
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
//webElement.sendKeys(Keys.TAB);
    }









    public static String corregirFormatoTexto(String textoIncorrecto){
        byte[] bytes = textoIncorrecto.getBytes(StandardCharsets.UTF_8);
        String textoCorrejido = new String(bytes, StandardCharsets.ISO_8859_1);

        return textoCorrejido;
    }
}
