# 🚀 GitHub Actions Setup Guide

Follow these steps to build your APK automatically using GitHub Actions.

---

## Step 1: Create a GitHub Account

If you don't have one, sign up at [github.com](https://github.com) (it's free).

---

## Step 2: Create a New Repository

1. Click the **"+"** icon in the top right → **"New repository"**
2. Name it `hymnal-app` (or any name you like)
3. Set it to **Public** (required for free GitHub Actions)
4. **DO NOT** check "Add a README" (we already have one)
5. Click **"Create repository"**

---

## Step 3: Upload the Code

### Option A: Using GitHub Web UI (Easiest)

1. Download and extract `HymnalApp.zip`
2. On your new repo page, click **"uploading an existing file"**
3. Drag and drop ALL files and folders from the extracted `HymnalApp` folder
4. Click **"Commit changes"**

### Option B: Using Git Command Line

```bash
# Extract the ZIP first, then:
cd HymnalApp

git init
git add .
git commit -m "Initial commit: Hymnal App"

git remote add origin https://github.com/YOUR_USERNAME/hymnal-app.git
git branch -M main
git push -u origin main
```

---

## Step 4: Watch the Build

1. Go to your repository on GitHub
2. Click the **"Actions"** tab
3. You'll see **"Build Android APK"** running
4. Wait 5-10 minutes for the build to complete ✅

---

## Step 5: Download Your APK

### From Artifacts (Any Branch)

1. Go to **Actions** tab
2. Click on the completed workflow run
3. Scroll to **"Artifacts"** section
4. Download `hymnal-app-debug-apk`
5. Extract the ZIP — your APK is inside!

### From Releases (Main Branch Only)

1. Go to **Releases** (right side of repo page)
2. Download the APK from the latest release

---

## 📁 What Gets Built?

| Artifact | When | Description |
|---|---|---|
| `hymnal-app-debug-apk` | Every push | Debug APK for testing |
| `hymnal-app-release-apk` | Main branch only | Unsigned release APK |
| GitHub Release | Main branch only | Tagged release with APK attached |

---

## 🔧 Customizing the Build

### Build Only on Specific Changes

Edit `.github/workflows/build.yml`:

```yaml
on:
  push:
    paths:
      - 'app/**'
      - '.github/workflows/**'
```

### Build Signed Release APK

1. Generate a keystore:
   ```bash
   keytool -genkey -v -keystore hymnal.keystore -alias hymnal -keyalg RSA -keysize 2048 -validity 10000
   ```

2. Add secrets to GitHub (Settings → Secrets and variables → Actions):
   - `KEYSTORE_BASE64` — Base64-encoded keystore
   - `KEYSTORE_PASSWORD` — Your keystore password
   - `KEY_ALIAS` — Key alias
   - `KEY_PASSWORD` — Key password

3. Update the workflow to sign the APK

---

## 🐛 Troubleshooting

| Problem | Solution |
|---|---|
| Build fails with "SDK location not found" | The workflow creates `local.properties` automatically — check the logs |
| "Gradle wrapper not found" | Make sure `gradlew` and `gradle/wrapper/` are uploaded |
| Build takes too long | Enable Gradle caching (already enabled in the workflow) |
| Out of build minutes | GitHub gives 2000 free minutes/month for private repos; **public repos are unlimited** |

---

## 📞 Need Help?

Open an issue on your repository and I'll help you debug!
