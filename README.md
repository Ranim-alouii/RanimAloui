# RanimAloui2 - Tunisian Heritage Quiz App

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-blue.svg)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-API%2024%2B-green.svg)](https://developer.android.com/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-2024.10.00-orange.svg)](https://developer.android.com/jetpack/compose)
[![Room](https://img.shields.io/badge/Room-2.6.1-purple.svg)](https://developer.android.com/training/data-storage/room)

An educational Android application that teaches users about Tunisian cultural heritage through interactive quizzes. Built with modern Android development practices using Kotlin and Jetpack Compose.

## 📱 Features

### 🎯 Core Functionality
- **Interactive Quizzes**: Multiple-choice questions on Tunisian history
- **Multiple Categories**: Six heritage periods (Roman, Carthaginian, Islamic, Ottoman, French Colonial, Modern Tunisia)
- **Difficulty Levels**: Easy, Medium, and Hard questions
- **Progress Tracking**: Score calculation and quiz completion statistics
- **Timer System**: 30-second timer per question for added challenge

### 🎨 User Experience
- **Modern UI**: Built with Jetpack Compose and Material Design 3
- **Intuitive Navigation**: Single-activity architecture with Compose Navigation
- **Responsive Design**: Optimized for various screen sizes
- **Smooth Animations**: Engaging transitions and visual feedback

### 🏗️ Technical Features
- **Offline Support**: Local data storage with Room database
- **MVVM Architecture**: Clean separation of concerns
- **Reactive Programming**: StateFlow for efficient state management
- **Type Safety**: Full Kotlin implementation with null safety

## 🏛️ Heritage Categories

| Category | Status | Description |
|----------|--------|-------------|
| Roman Heritage | ✅ Complete | Questions about Roman period in Tunisia |
| Carthaginian Heritage | 🚧 Planned | Ancient Carthaginian civilization |
| Islamic Heritage | 🚧 Planned | Islamic Golden Age in Tunisia |
| Ottoman Heritage | 🚧 Planned | Ottoman rule period |
| French Colonial Heritage | 🚧 Planned | French colonial era |
| Modern Tunisia | 🚧 Planned | Contemporary Tunisian history |

## 🛠️ Technology Stack

### Core Technologies
- **Language**: Kotlin 2.0.21
- **UI Framework**: Jetpack Compose 2024.10.00
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room 2.6.1
- **Navigation**: Navigation Compose 2.8.3

### Development Tools
- **Build System**: Gradle with Kotlin DSL
- **Dependency Management**: Version Catalogs (libs.versions.toml)
- **Compiler Plugin**: KSP (Kotlin Symbol Processing) 2.0.21-1.0.27

### Testing
- **Unit Testing**: JUnit 4, Mockito 5.11.0
- **UI Testing**: Espresso 3.6.1
- **Coroutines Testing**: Kotlinx Coroutines Test 1.8.1

## 📁 Project Structure

```
app/src/main/java/com/example/ranimaloui/
├── data/                    # Data layer
│   ├── AppDatabase.kt      # Room database configuration
│   ├── HeritageRepository.kt # Data repository
│   ├── Models.kt           # Data models and enums
│   ├── QuestionDao.kt      # Database access object
│   └── QuestionEntity.kt   # Database entity
├── ui/                     # UI layer
│   ├── screens/            # Screen components
│   │   ├── CategorySelectionScreen.kt
│   │   ├── DifficultySelectionScreen.kt
│   │   ├── MainMenuScreen.kt
│   │   ├── QuizScreen.kt
│   │   ├── ResultsScreen.kt
│   │   ├── SplashScreen.kt
│   │   └── UnderConstructionScreen.kt
│   └── theme/              # App theming
└── viewmodel/              # Business logic layer
    └── QuizViewModel.kt    # Quiz state management
```

## 🚀 Getting Started

### Prerequisites
- **Android Studio**: Iguana or later
- **Minimum SDK**: API 24 (Android 7.0)
- **Java Version**: 17
- **Gradle**: 8.7.2

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/ranimaloui2.git
   cd ranimaloui2
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the cloned directory and select it

3. **Sync Project**
   - Android Studio will automatically sync the project with Gradle
   - Wait for all dependencies to download

4. **Run the Application**
   - Connect an Android device or start an emulator
   - Click the "Run" button (green play icon) in Android Studio
   - Select your target device/emulator

### Build Variants

The project supports different build variants:

- **debug**: Development build with debugging enabled
- **release**: Production build with optimizations

## 🧪 Testing

### Running Tests

#### Unit Tests
```bash
./gradlew testDebugUnitTest
```

#### UI Tests
```bash
./gradlew connectedDebugAndroidTest
```

#### All Tests
```bash
./gradlew test
```

### Test Coverage

The application includes comprehensive testing:

- **ViewModel Tests**: Business logic validation
- **Repository Tests**: Data layer verification
- **UI Tests**: User interaction testing
- **Integration Tests**: End-to-end functionality

## 📊 Database Schema

### Questions Table

| Column | Type | Description |
|--------|------|-------------|
| id | INTEGER (PK) | Unique question identifier |
| category | TEXT | Heritage category (enum value) |
| difficulty | TEXT | Difficulty level (enum value) |
| questionText | TEXT | Question content |
| correctAnswer | TEXT | Correct answer text |
| imageResName | TEXT | Associated image resource |

## 🎯 Usage

### Navigation Flow

1. **Splash Screen**: App introduction
2. **Main Menu**: Access to quiz features
3. **Category Selection**: Choose heritage category
4. **Difficulty Selection**: Select quiz difficulty
5. **Quiz Screen**: Answer questions within time limit
6. **Results Screen**: View final score and statistics

### Quiz Mechanics

- Each question has a 30-second time limit
- Multiple choice answers (4 options)
- Immediate feedback on answer selection
- Score tracking throughout the quiz
- Progress indicator showing current question

## 🔧 Configuration

### Build Configuration

Key settings in `app/build.gradle.kts`:

```kotlin
android {
    compileSdk = 35
    defaultConfig {
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}
```

### Dependencies

Major dependencies are managed through `gradle/libs.versions.toml`:

- **Compose BOM**: UI components and tooling
- **Room**: Database persistence
- **Navigation**: Screen navigation
- **Lifecycle**: ViewModel and LiveData
- **Testing**: Unit and UI testing frameworks

## 🤝 Contributing

We welcome contributions to RanimAloui2! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Development Guidelines

- Follow Kotlin coding conventions
- Write unit tests for new features
- Update documentation as needed
- Ensure code passes all existing tests

## 📈 Future Roadmap

### Phase 1: Core Completion ✅
- [x] Roman Heritage quiz implementation
- [x] Basic UI/UX design
- [x] Database integration
- [x] MVVM architecture

### Phase 2: Feature Expansion 🚧
- [ ] Complete all heritage categories
- [ ] User account system
- [ ] Progress tracking
- [ ] Achievements system

### Phase 3: Advanced Features 📅
- [ ] Social features (leaderboards)
- [ ] Multi-language support
- [ ] Offline mode improvements
- [ ] Advanced analytics

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- **Tunisian Cultural Heritage**: For inspiring this educational project
- **Android Developer Community**: For best practices and documentation
- **Jetpack Compose Team**: For the excellent UI framework

## 📞 Support

For questions, issues, or contributions:

- **Issues**: [GitHub Issues](https://github.com/your-username/ranimaloui2/issues)
- **Discussions**: [GitHub Discussions](https://github.com/your-username/ranimaloui2/discussions)
- **Email**: [your-email@example.com](mailto:your-email@example.com)

---

**Made with ❤️ for Tunisian cultural heritage education**