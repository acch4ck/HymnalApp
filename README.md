# Hymns of Faith — Android Hymnal App

A complete, production-ready Android hymnal app built with **Kotlin + Jetpack Compose + Room + Paging 3**.

## Features

- **50 classic hymns** pre-loaded into a local Room database
- **Beautiful home screen** with 2-column grid of hymn cards
- **Real-time search** across titles, lyrics, and hymn numbers
- **Sort options**: by number or alphabetically
- **Favorites**: mark hymns and filter to favorites only
- **Hymn detail screen** with full lyrics, author, and category
- **Paging 3** for efficient data loading
- **Material 3** design with dark theme support

## Tech Stack

| Component | Technology |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose (Material 3) |
| Database | Room |
| Paging | Paging 3 |
| Navigation | Navigation Compose |
| Async | Kotlin Coroutines + Flow |

## Project Structure

```
app/src/main/java/com/faith/hymnal/
├── HymnalApplication.kt          # Application class (DI container)
├── MainActivity.kt               # Single activity, NavHost
├── data/
│   ├── HymnData.kt               # 50 hymns seed data
│   ├── HymnRepository.kt         # Repository pattern
│   └── local/
│       ├── Hymn.kt               # Room entity
│       ├── HymnDao.kt            # Room DAO with PagingSource
│       └── HymnDatabase.kt       # Room database
└── ui/
    ├── HymnViewModel.kt          # Shared ViewModel
    ├── theme/Theme.kt            # Material 3 theme
    ├── home/HomeScreen.kt        # Home + search + grid
    └── detail/HymnDetailScreen.kt # Hymn lyrics screen
```

## How to Build the APK

### Option 1: Android Studio (Recommended)

1. **Open** Android Studio
2. **File → Open** → select the `HymnalApp` folder
3. Wait for Gradle sync to complete
4. **Build → Build Bundle(s) / APK(s) → Build APK(s)**
5. Click **"locate"** when done — the APK is at:
   ```
   app/build/outputs/apk/debug/app-debug.apk
   ```

### Option 2: Command Line

```bash
cd HymnalApp

# On macOS/Linux:
./gradlew assembleDebug

# On Windows:
gradlew.bat assembleDebug
```

The APK will be at `app/build/outputs/apk/debug/app-debug.apk`

### Option 3: Release APK (Signed)

1. In Android Studio: **Build → Generate Signed Bundle / APK**
2. Select **APK**
3. Create a new keystore (or use existing)
4. Select **release** build variant
5. Click **Create**

## Installing on a Device

```bash
# Enable USB debugging on your phone, then:
adb install app/build/outputs/apk/debug/app-debug.apk
```

Or copy the APK to your phone and tap to install (allow unknown sources).

## Adding More Hymns

Edit `app/src/main/java/com/faith/hymnal/data/HymnData.kt` and add more `Hymn(...)` entries to the list. The database seeds automatically on first launch.

## Screenshots

| Home Screen | Hymn Detail |
|---|---|
| Grid of hymns with search bar | Full lyrics with favorites |


---

## ☁️ Build with GitHub Actions (No Android Studio Required!)

Don't want to install Android Studio? Use GitHub Actions to build your APK in the cloud for **free**!

📖 **[See the full setup guide →](GITHUB_SETUP.md)**

### Quick Start

1. Create a GitHub repository
2. Upload this code
3. Go to **Actions** tab → watch it build
4. Download your APK from **Artifacts**

It's that simple! 🎉
