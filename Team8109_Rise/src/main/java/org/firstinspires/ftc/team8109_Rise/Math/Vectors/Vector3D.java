package org.firstinspires.ftc.team8109_Rise.Math.Vectors;

public class Vector3D {
    private static final Vector3D ZERO = new Vector3D(0, 0, 0);

    public double A, B, C;

    public Vector3D(double A, double B, double C){
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public void set_î(double A) {
        this.A = A;
    }

    public void set_Ĵ(double B) {
        this.B = B;
    }

    public void set_k̂(double C) {
        this.C = C;
    }
    
    public void set(double A, double B, double C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public void set(Vector3D v) {
        A = v.A;
        B = v.B;
        C = v.C;
    }

    public Vector3D clone(){
        return new Vector3D(A, B, C);
    }

    public Vector3D add(Vector3D v) {
        return new Vector3D(A+v.A, B+v.B, C+v.C);
    }

    public Vector3D add(double A, double B, double C) {

        return new Vector3D(this.A+A, this.B+B, this.C+C);
    }

    public Vector3D subtract(Vector3D v) {
        return new Vector3D(A -v.A, B -v.B, C-v.C);
    }

    public Vector3D subtract(double A, double B, double C) {
        return new Vector3D(this.A-A, this.B-B, this.C-C);
    }

    public Vector3D scale(double scalar){
        return new Vector3D(A *scalar, B*scalar, C*scalar);
    }

    public Vector3D normalize() {
        return scale(1/getMagnitude());
    }

    public double getMagnitude(){
        return Math.sqrt((A * A) + (B * B) + (C * C));
    }

    public double DotProduct(Vector3D v){
        return A *v.A + B *v.B + C *v.C;
    }

    public double DotProduct(double A, double B, double C){
        return this.A*A + this.B*B + this.C*C;
    }

    // Figure out directions
    public double CrossProduct(Vector3D v){
        return (A*v.B) - (B*v.A);
    }

    public static Vector3D ZERO(){
        return ZERO.clone();
    }



    public void set(Vector2D v, double C) {
        this.A = v.A;
        this.B = v.B;
        this.C = C;
    }

//    public Vector2 getVector2(){
//        return new Vector2(a, b);
//    }
//

//
//    public boolean equals(Vector3 vector3){
//        return (vector3.a == a) && (vector3.b == b) && (vector3.c == c);
//    }
//
//    public Vector4 getVector4(double d){
//        return new Vector4(a, b, c, d);
//    }
//
    @Override
    public String toString() {
        return A + ", " + B + ", " + C;
    }

}
