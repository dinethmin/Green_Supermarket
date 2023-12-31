package Dao;
/*
import Connection.PayPalConfig;
import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.*;
import com.paypal.http.HttpResponse;
import java.io.IOException;
import java.util.ArrayList;

public class PayPalPaymentHandler {

    public static void createOrder() {

        PayPalHttpClient client = PayPalConfig.getClient();

        OrdersCreateRequest request = new OrdersCreateRequest();
        request.prefer("return=representation");

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");
        // Set up order details: items, amount, currency, etc.
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item().name("Item 1").unitAmount(new Money().currencyCode("USD").value("50.00")).quantity("2"));
        items.add(new Item().name("Item 2").unitAmount(new Money().currencyCode("USD").value("25.00")).quantity("1"));

        AmountBreakdown amountBreakdown = new AmountBreakdown()
                .itemTotal(new Money().currencyCode("USD").value("125.00")) // Total value of all items
                .shipping(new Money().currencyCode("USD").value("10.00")) // Shipping cost
                .taxTotal(new Money().currencyCode("USD").value("15.00")); // Tax amount

        // Summing item prices for the subtotal
        double subtotal = Double.parseDouble(amountBreakdown.itemTotal().value())
                + Double.parseDouble(amountBreakdown.shipping().value())
                + Double.parseDouble(amountBreakdown.taxTotal().value());

        //amountBreakdown.setSubtotal(new Money().currencyCode("USD").value(String.valueOf(subtotal))); // Subtotal value
         
        // Add items to the orderRequest
        orderRequest.purchaseUnits(new ArrayList<PurchaseUnitRequest>() {
            {
                add(new PurchaseUnitRequest()
                        .amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value(String.valueOf(subtotal)).amountBreakdown(amountBreakdown))
                        .items(items));
            }
        });

        request.requestBody(orderRequest);

        try {
            HttpResponse<Order> response = client.execute(request);
            if (response.statusCode() == 201) {
                Order createdOrder = response.result();
                // Extract created order ID or handle the order object
            } else {
                // Handle error response
            }
        } catch (IOException e) {
            // Handle exception
        }
    }

    public static void executePayment(String orderId) {
        PayPalHttpClient client = PayPalConfig.getClient();

        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);
        request.requestBody(new OrderRequest());

        try {
            HttpResponse<Order> response = client.execute(request);
            if (response.statusCode() == 201) {
                Order capturedOrder = response.result();
                System.out.println("Payment executed successfully. Order ID: " + capturedOrder.id());
            
                // Handle the captured order or payment success
            } else {
                // Handle error response
                System.err.println("Payment execution failed with status code: " + response.statusCode());
            
            }
        } catch (IOException e) {
            // Handle exception
            System.err.println("Exception while executing payment: " + e.getMessage());
        
        }
    }
}
*/