package com.cfsuman.ontologyexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import com.github.ontio.OntSdk
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Test Net
        val rpc = "http://dappnode1.ont.io:20336"
        val restful = "http://dappnode1.ont.io:20334"

        // Initialize ontology sdk
        val ontSdk = OntSdk.getInstance()
        ontSdk.setRpc(rpc)
        ontSdk.setRestful(restful)
        ontSdk.setDefaultConnect(ontSdk.restful)

        btn.setOnClickListener {
            doAsync {
                val height = ontSdk.connect.blockHeight

                uiThread {
                    tvHeight.text = "Block Height: " + height
                }
            }


            doAsync {
                //********************************************************************************************** problem is here, walletMgr return null object *****************
                val acct = ontSdk.walletMgr.createAccount("name", "password")
                ontSdk.walletMgr.writeWallet()

                uiThread {
                    tv.text = "address: " + acct.address
                }
            }

        }
    }
}
