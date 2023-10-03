package es.ieslosmontecillos.calculadoraprestamo;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class CalculadoraPrestamo extends Application {

    private TextField interestRateTextField;
    private TextField yearsTextField;
    private TextField loanAmountTextField;
    private TextField monthlyPayment;
    private TextField totalPayment;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora de Préstamo");

        // Crear los componentes de la interfaz
        Label interestRateLabel = new Label("Annual Interest Rate:");
        Label yearsLabel = new Label("Number of years:");
        Label loanAmountLabel = new Label("Loan Amount:");
        Label monthlyPaymentTextLabel = new Label("Monthly payment:");
        Label totalPaymentTextLabel = new Label("Total payment:");
        interestRateTextField = new TextField();
        yearsTextField = new TextField();
        loanAmountTextField = new TextField();
        monthlyPayment = new TextField();
        totalPayment = new TextField();
        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(e -> calculateMonthlyPayment());
        interestRateTextField.setAlignment(Pos.CENTER_RIGHT);
        yearsTextField.setAlignment(Pos.CENTER_RIGHT);
        loanAmountTextField.setAlignment(Pos.CENTER_RIGHT);
        monthlyPayment.setAlignment(Pos.CENTER_RIGHT);
        totalPayment.setAlignment(Pos.CENTER_RIGHT);
        monthlyPayment.setEditable(false);
        totalPayment.setEditable(false);

        // Crear el grid y configurar las columnas y filas
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(column1, column2);

        // Agregar los componentes al grid con alineación hacia la derecha
        gridPane.add(interestRateLabel, 0, 0);
        gridPane.add(interestRateTextField, 1, 0);
        gridPane.add(yearsLabel, 0, 1);
        gridPane.add(yearsTextField, 1, 1);
        gridPane.add(loanAmountLabel, 0, 2);
        gridPane.add(loanAmountTextField, 1, 2);
        gridPane.add(monthlyPaymentTextLabel, 0, 3);
        gridPane.add(monthlyPayment, 1, 3);
        gridPane.add(totalPaymentTextLabel, 0, 4);
        gridPane.add(totalPayment, 1, 4);

        // Configurar el botón y agregarlo al grid
        HBox buttonBox = new HBox(calculateButton);
        buttonBox.setAlignment(Pos.BASELINE_RIGHT);
        gridPane.add(buttonBox, 1, 5);
        GridPane.setHalignment(buttonBox, HPos.RIGHT);

        // Crear la escena y mostrarla
        Scene scene = new Scene(gridPane, 300, 220);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateMonthlyPayment() {
        double interestRate = Double.parseDouble(interestRateTextField.getText());
        int years = Integer.parseInt(yearsTextField.getText());
        double loanAmount = Double.parseDouble(loanAmountTextField.getText());
        double monthlyInterestRate = interestRate / (100 * 12);
        int numberOfPayments = years * 12;

        double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
        double totalPayment = monthlyPayment * numberOfPayments;

        this.monthlyPayment.setText(String.format("%.2f€", monthlyPayment));
        this.totalPayment.setText(String.format("%.2f€", totalPayment));
    }
}
