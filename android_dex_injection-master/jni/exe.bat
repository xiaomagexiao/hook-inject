
echo execute mk
for %%i in ("%~dp0\..\.") do (
  Copy /y ..\libs\armeabi\bin E:\git-workspace\hook-inject\bin\%%~ni
  Copy /y ..\libs\armeabi\libbin.so E:\git-workspace\hook-inject\bin\%%~ni.so
  )
ndk-build
echo xxx
pause

