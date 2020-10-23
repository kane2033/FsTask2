package com.fosusstart.task2.ui.main.contentProvider

import android.content.ContentResolver
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fosusstart.task2.R


class ContentProviderFragment : Fragment(){

    companion object {
        fun newInstance() = ContentProviderFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true // Сохранение информации при перевороте
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.content_provider_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contacts: List<ContactItem> = getContacts()
        // Инициализация RecyclerView
        view.findViewById<RecyclerView>(R.id.contacts_recycler_view).apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ContactsDataAdapter(contacts)
        }
    }

    // Метод получения контактов на телефоне
    private fun getContacts(): List<ContactItem> {
        val items: ArrayList<ContactItem> = ArrayList()
        val resolver: ContentResolver? = context?.contentResolver
        val cursor = resolver?.query(ContactsContract.Contacts.CONTENT_URI, null, null, null,
            null)

        if (cursor != null) {
            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val phoneNumber = (cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))).toInt()

                    if (phoneNumber > 0) {
                        val cursorPhone = resolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", arrayOf(id), null)

                        if(cursorPhone != null && cursorPhone.count > 0) {
                            while (cursorPhone.moveToNext()) {
                                val phone = cursorPhone.getString(
                                    cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                                // Заполнение массива контактов
                                items.add(ContactItem(name, phone))
                            }
                        }
                        cursorPhone?.close()
                    }
                }
            } else {
                Toast.makeText(context, "Нет контактов", Toast.LENGTH_SHORT).show()
            }
        }
        cursor?.close()
        return items
    }

}