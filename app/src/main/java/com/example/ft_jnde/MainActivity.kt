package com.example.ft_jnde

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ft_jnde.databinding.ActivityMainBinding
import com.example.ft_jnde.databinding.ItemUserBinding
import java.util.Locale
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)
        binding.usersRecycler.adapter = adapter
        binding.usersRecycler.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        adapter.submit(generateSampleUsers())
        binding.searchEditText.addTextChangedListener { adapter.filter(it?.toString().orEmpty()) }
    }

    private fun generateSampleUsers(): List<UserUi> {
        val base = listOf("mariaj", "abouali", "johndoe", "janed", "ftuser", "kbensaid", "nlavoie", "zayd", "rbrown", "lgreen", "hkim", "tdupont", "yamada", "garcia", "silva")
        return base.map { login ->
            val level = Random.nextDouble(0.0, 21.0)
            val wallet = Random.nextInt(0, 120)
            UserUi(login, level, wallet)
        }
    }
}

data class UserUi(
    val login: String,
    val level: Double,
    val wallet: Int
)

class UsersAdapter(
    private val onItemClick: (UserUi) -> Unit
) : Adapter<UsersAdapter.VH>() {

    private val full = mutableListOf<UserUi>()
    private val visible = mutableListOf<UserUi>()

    fun submit(list: List<UserUi>) {
        full.clear()
        full.addAll(list)
        visible.clear()
        visible.addAll(list)
        notifyDataSetChanged()
    }

    fun filter(q: String) {
        val query = q.trim().lowercase(Locale.getDefault())
        visible.clear()
        if (query.isEmpty()) {
            visible.addAll(full)
        } else {
            visible.addAll(full.filter { it.login.lowercase(Locale.getDefault()).contains(query) })
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(visible[position])
    }

    override fun getItemCount(): Int = visible.size

    class VH(
        private val binding: ItemUserBinding,
        private val onItemClick: (UserUi) -> Unit
    ) : ViewHolder(binding.root) {
        fun bind(item: UserUi) {
            binding.nameText.text = item.login
            binding.levelText.text = "Level %02.2f".format(item.level)
            binding.walletText.text = "Wallet ${item.wallet}"
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }
}
