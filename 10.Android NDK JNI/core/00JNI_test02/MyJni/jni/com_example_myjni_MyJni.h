/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_example_myjni_MyJni */

#ifndef _Included_com_example_myjni_MyJni
#define _Included_com_example_myjni_MyJni
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_example_myjni_MyJni
 * Method:    MyJniTestA
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_example_myjni_MyJni_MyJniTestA
  (JNIEnv *, jobject);

/*
 * Class:     com_example_myjni_MyJni
 * Method:    MyJniTestB
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_example_myjni_MyJni_MyJniTestB
  (JNIEnv *, jobject, jstring);

/*
 * Class:     com_example_myjni_MyJni
 * Method:    MyJniTestC
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_myjni_MyJni_MyJniTestC
  (JNIEnv *, jobject, jstring);

/*
 * Class:     com_example_myjni_MyJni
 * Method:    MyJniFunA
 * Signature: (IFB)I
 */
JNIEXPORT jint JNICALL Java_com_example_myjni_MyJni_MyJniFunA
  (JNIEnv *, jobject, jint, jfloat, jbyte);

/*
 * Class:     com_example_myjni_MyJni
 * Method:    MyJniFunB
 * Signature: (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_myjni_MyJni_MyJniFunB
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     com_example_myjni_MyJni
 * Method:    MyJniFunC
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_example_myjni_MyJni_MyJniFunC
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     com_example_myjni_MyJni
 * Method:    MyJniFunAA
 * Signature: ([I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_myjni_MyJni_MyJniFunAA
  (JNIEnv *, jobject, jintArray);

/*
 * Class:     com_example_myjni_MyJni
 * Method:    MyJniFunAB
 * Signature: ([I)[I
 */
JNIEXPORT jintArray JNICALL Java_com_example_myjni_MyJni_MyJniFunAB
  (JNIEnv *, jobject, jintArray);

/*
 * Class:     com_example_myjni_MyJni
 * Method:    MyJniFunAC
 * Signature: ([Ljava/lang/String;)[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_com_example_myjni_MyJni_MyJniFunAC
  (JNIEnv *, jobject, jobjectArray);

#ifdef __cplusplus
}
#endif
#endif
