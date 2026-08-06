# Uygulama Yükleme Ekranında Kalma Sorunu Çözümü

Uygulamanın açılışta yükleme ekranında (Splash) takılı kalmasının temel nedeni, `AppNavigation` içerisinde `sessionState` değerinin `NavHost` dışından gözlemlenmesi ve bu değerin navigasyon grafiğine statik bir parametre olarak geçilmesidir. `sessionState` değiştiğinde `AppNavigation` yeniden oluşturulsa da, `NavHost` içerisindeki mevcut route içeriği (Splash) her zaman bu değişikliği doğru şekilde yakalayamayabilir.

Ayrıca, projenin bazı kısımlarında `javax.inject.Inject` yerine `jakarta.inject.Inject` kullanıldığı tespit edilmiştir. Hilt/Dagger projelerinde tutarlılık ve olası enjeksiyon hatalarını önlemek adına `javax.inject.Inject` kullanımı tercih edilmelidir.

## Önerilen Değişiklikler

### 1. Navigasyon Mantığının İyileştirilmesi

`AppNavigation.kt` dosyasını, `sessionState` durumunu doğrudan Splash route'u içerisinde gözlemleyecek şekilde güncelleyeceğiz. Bu sayede durum değişiklikleri anında yakalanacak ve yönlendirme (navigasyon) işlemi tetiklenecektir.

#### [MODIFY] [AppNavigation.kt](file:///C:/Users/Muhammet Ali Balci/AndroidStudioProjects/IsgMobil2/app/src/main/java/com/alibbalci/isgmobil/navigation/AppNavigation.kt)
- `sessionState` gözlemleme işlemini `AppNavigation` üst seviyesinden alıp, `splashScreen` composable içeriğine taşıyacağız.
- `NavHost` başlangıç hedefi olarak Splash route'u kalmaya devam edecek ancak içeriği daha dinamik olacak.

### 2. Bağımlılık Enjeksiyonu Düzeltmeleri

`jakarta.inject.Inject` kullanan dosyaları `javax.inject.Inject` kullanacak şekilde güncelleyeceğiz.

#### [MODIFY] [HomeViewModel.kt](file:///C:/Users/Muhammet Ali Balci/AndroidStudioProjects/IsgMobil2/app/src/main/java/com/alibbalci/isgmobil/presentation/home/HomeViewModel.kt)
#### [MODIFY] [RegisterViewModel.kt](file:///C:/Users/Muhammet Ali Balci/AndroidStudioProjects/IsgMobil2/app/src/main/java/com/alibbalci/isgmobil/presentation/auth/register/RegisterViewModel.kt)
#### [MODIFY] [LoginUseCase.kt](file:///C:/Users/Muhammet Ali Balci/AndroidStudioProjects/IsgMobil2/app/src/main/java/com/alibbalci/isgmobil/domain/usecase/auth/LoginUseCase.kt)
#### [MODIFY] [RegisterUseCase.kt](file:///C:/Users/Muhammet Ali Balci/AndroidStudioProjects/IsgMobil2/app/src/main/java/com/alibbalci/isgmobil/domain/usecase/auth/RegisterUseCase.kt)

### 3. Oturum Yönetimi ve Hata Yakalama (Opsiyonel ama Önerilen)

`SessionViewModel.kt` dosyasına hata yakalama ekleyerek DataStore kaynaklı olası bir kilitlenmeyi önleyeceğiz.

#### [MODIFY] [SessionViewModel.kt](file:///C:/Users/Muhammet Ali Balci/AndroidStudioProjects/IsgMobil2/app/src/main/java/com/alibbalci/isgmobil/presentation/session/SessionViewModel.kt)
- Flow akışına `.catch` bloğu eklenerek olası hatalarda `LoggedOut` durumuna düşmesi sağlanacak.

## Doğrulama Planı

### Manuel Doğrulama
- Uygulama başlatıldığında Splash ekranından sonra (token varsa) Ana Sayfaya (Home) veya (token yoksa) Giriş Ekranına (Login) yönlendiği kontrol edilecek.
- Giriş yapıldıktan sonra uygulama kapatılıp açıldığında direkt Ana Sayfaya düştüğü doğrulanacak.
- Çıkış yapıldığında Splash üzerinden tekrar Login'e yönlendirme yapıldığı kontrol edilecek.
