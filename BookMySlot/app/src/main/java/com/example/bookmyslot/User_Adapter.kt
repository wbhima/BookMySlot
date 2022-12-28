package com.example.bookmyslot
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.bookmyslot.MainActivity
import com.example.bookmyslot.R
import com.google.firebase.auth.FirebaseAuth

class User_Adapter(val context: Context,val userlist:ArrayList<User>):
    RecyclerView.Adapter<User_Adapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view :View=LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser=userlist[position]
        holder.textName.text=currentUser.name
        holder.itemView.setOnClickListener {
            val intent= Intent(context,EventForm::class.java)
            intent.putExtra("name",currentUser.name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
val textName= itemView.findViewById<TextView>(R.id.txt_name)
    }
}