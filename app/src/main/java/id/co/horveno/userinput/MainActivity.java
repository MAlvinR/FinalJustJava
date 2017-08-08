package id.co.horveno.userinput;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {


    /*Jumlah pesanan 'default' adalah kosong*/
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method ini bisa berfungsi jika tombol plus untuk menambah pesanan diklik
     * increment berasal dari nama onClick di tombol plus
     */
    public void tambahPesanan(View view) {
        if (quantity == 100) {
            return;
        }
        quantity = quantity + 1;
        jumlahPesanan(quantity);
    }

    /**
     * Method ini bisa berfungsi jika tombol kurang untuk mengurangi pesanan diklik
     * decrement berasal dari nama onClick di tombol kurang
     */
    public void kurangiPesanan(View view) {
        if (quantity == 1) {
            return;
        }
        quantity = quantity - 1;
        jumlahPesanan(quantity);
    }

    /**
     * Tombol ini berfungsi ketika tombol order diklik
     * submitOrder berasal dari nama onClick di tombol order
     */
    public void submitOrder(View view) {
        // Inisialisasi widget EditText
        EditText nameField = (EditText) findViewById(R.id.name_field);
        /*Mendapatkan string dari edittext namefield*/
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();

        // Mengecek keadaan jika user ingin topping Whipped Cream
        CheckBox ayamBakarCheckBox = (CheckBox) findViewById(R.id.ayam_bakar_checkbox);
        boolean tambahAyamBakar = ayamBakarCheckBox.isChecked();

        // Mengecek keadaan jika user ingin topping Chocholate
        CheckBox telorBebekCheckBox = (CheckBox) findViewById(R.id.telor_bebek_checkbox);
        boolean tambahTelorBebek = telorBebekCheckBox.isChecked();

        // Mengkalkulasikan harganya dengan mengirim datanya ke method calculatePrice
        int price = hitungHarga(tambahAyamBakar, tambahTelorBebek);

        // Menampilkan info data yang sudah user pesan dan
        String message = createOrderSummary(name, price, tambahAyamBakar, tambahTelorBebek);
        //Menampilkan data dari string message di dalam method displayMessage()
        hasilPesanan(message);
    }

    /**
     * Calculates the price of the order.
     *
     * @param tambahAyamBakar is whether or not we should include whipped cream topping in the price
     * @param tambahTelorBebek    is whether or not we should include chocolate topping in the price
     * @return total price
     */
    private int hitungHarga(boolean tambahAyamBakar, boolean tambahTelorBebek) {
        /*Pertama menghitung harga dasar dari sepiring Nasi*/
        int basePrice = 3000;

        // Kalau user ingin menambahkan lauk Ayam Goreng, maka tambahkan harga Rp10000
        if (tambahAyamBakar) {
            basePrice = basePrice + 10000;
        }

        // Kalau user ingin menambahkan lauk Telor Bebek, maka tambahkan harga Rp4000
        if (tambahTelorBebek) {
            basePrice = basePrice + 4000;
        }

        /*Menghitung total harga dengan mengalikan jumlah pesanan dengan harga dasarnya*/
        return quantity * basePrice;
    }

    private String createOrderSummary(String name, int price, boolean tambahAyamBakar,
                                      boolean tambahTelorBebek) {

        /*\n berfungsi untuk memberi jarak enter pada string. Contohnya bisa dilihat pada hasilnya*/
        String priceMessage = "Nama : " + name + "\n";
        priceMessage = priceMessage + "Jumlah : " + quantity + "\n";
        priceMessage = priceMessage + "Ayam Goreng " + tambahAyamBakar + "\n";
        priceMessage = priceMessage + "Telor Bebek " + tambahTelorBebek + "\n";
        priceMessage = priceMessage + "Total : Rp" + price + "\n";
        priceMessage = priceMessage + "Terima kasih telah memilih kepercayaan kepada kami!";
        return priceMessage;
    }

    public void hasilPesanan(String message) {
        TextView showOrderSummary = (TextView) findViewById(R.id.showOrderSummary);
        showOrderSummary.setText(message);
    }

    /**
     * Method ini menampilkan jumlah pesanan yang telah dipesan
     */
    private void jumlahPesanan(int jumlahPesanan) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + jumlahPesanan);
    }
}
