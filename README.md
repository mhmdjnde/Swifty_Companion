# Swifty_Companion

A modern Android app in Kotlin that lets you search 42 logins and view user details with a clean, responsive UI.  
Current milestone focuses on the **Home screen** (search + results). Upcoming milestones integrate **42 Intra OAuth2** and detailed profile views.

## ✨ Features (current)
- Top app bar titled **Ft_JnDe**
- Search box under the top bar with live filtering
- Results rendered in a **RecyclerView** (not ListView)
- Each item shows: **login**, **Level xx.xx**, and **Wallet points**
- Responsive, modern UI using **ConstraintLayout** + Material design
- Data seeded from Kotlin for now (no network)

## 🧭 Roadmap (next)
- Profile screen with ≥4 user details and avatar
- OAuth2 with 42 Intra (token reuse, no token-per-query)
- Token auto-refresh on expiry
- Error states: user not found, network, timeouts
- Skills with level and percentage
- Projects list including failed ones
- Light/dark theming and basic accessibility pass

## 🧱 Tech Stack
- Kotlin, Android Studio (AGP with Compose enabled, current UI via ViewBinding + ConstraintLayout)
- Material 3 components
- RecyclerView
- ViewBinding (for XML layouts)
- Compose enabled for future components

## 📂 Project Structure (app)
