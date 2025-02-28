package com.example.api_sell_clothes.Utils;

import com.example.api_sell_clothes.DTO.Auth.*;
import com.example.api_sell_clothes.Entity.Users;

import java.util.regex.Pattern;

public class AuthValidationUtils {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_PATTERN = "^[0-9]{10}$";
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MIN_USERNAME_LENGTH = 3;
    private static final String DEFAULT_SUPER_ADMIN = "2";

    // Basic field validations
    private static void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được để trống");
        }
        if (username.length() < MIN_USERNAME_LENGTH) {
            throw new IllegalArgumentException("Tên đăng nhập phải có ít nhất " + MIN_USERNAME_LENGTH + " ký tự");
        }
//        if (!username.matches("^[a-zA-Z0-9._-]{3,}$")) {
//            throw new IllegalArgumentException("Tên đăng nhập chỉ được chứa chữ cái, số và các ký tự ._-");
//        }
    }

    private static void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu không được để trống");
        }
//        if (password.length() < MIN_PASSWORD_LENGTH) {
//            throw new IllegalArgumentException("Mật khẩu phải có ít nhất " + MIN_PASSWORD_LENGTH + " ký tự");
//        }
//        if (!password.matches(".*[A-Z].*")) {
//            throw new IllegalArgumentException("Mật khẩu phải chứa ít nhất một chữ hoa");
//        }
//        if (!password.matches(".*[a-z].*")) {
//            throw new IllegalArgumentException("Mật khẩu phải chứa ít nhất một chữ thường");
//        }
//        if (!password.matches(".*[0-9].*")) {
//            throw new IllegalArgumentException("Mật khẩu phải chứa ít nhất một chữ số");
//        }
//        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
//            throw new IllegalArgumentException("Mật khẩu phải chứa ít nhất một ký tự đặc biệt");
//        }
    }

    private static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống");
        }
        if (!Pattern.compile(EMAIL_PATTERN).matcher(email).matches()) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }
    }

    private static void validatePhone(String phone) {
        if (phone != null && !phone.trim().isEmpty() && !Pattern.compile(PHONE_PATTERN).matcher(phone).matches()) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ (phải có 10 chữ số)");
        }
    }

    private static void validateFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Họ tên không được để trống");
        }
        if (fullName.length() < 2) {
            throw new IllegalArgumentException("Họ tên phải có ít nhất 2 ký tự");
        }
    }

    // Validate basic registration data
    private static void validateBasicRegistrationData(String username, String password, String email, String phone, String fullName) {
        validateUsername(username);
        validatePassword(password);
        validateEmail(email);
        validatePhone(phone);
        validateFullName(fullName);
    }

    // Public validation methods for different request types
    public static void validateAuthRequest(AuthRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Thông tin đăng nhập không được để trống");
        }
        validateUsername(request.getUsername());
        validatePassword(request.getPassword());
    }

    public static void validateRegisterRequest(RegisterRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Thông tin đăng ký không được để trống");
        }
        validateBasicRegistrationData(
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                request.getPhone(),
                request.getFullName()
        );
    }

    public static void validateRegisterAdminRequest(RegisterAdminRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Thông tin đăng ký admin không được để trống");
        }
        validateBasicRegistrationData(
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                request.getPhone(),
                request.getFullName()
        );
    }

    public static void validateRegisterSuperAdminRequest(RegisterSuperAdminRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Thông tin đăng ký super admin không được để trống");
        }
        validateBasicRegistrationData(
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                request.getPhone(),
                request.getFullName()
        );
    }

    public static void validateResetSuperAdminPassword(ResetSuperAdminPasswordRequest request, Users user) {
        if (request == null || request.getNewPassword() == null) {
            throw new IllegalArgumentException("Thông tin đổi mật khẩu không được để trống");
        }
        validatePassword(request.getNewPassword());

        if (user == null) {
            throw new IllegalArgumentException("Không tìm thấy tài khoản Super Admin");
        }
    }

    public static void validateDeleteAdmin(Users user) {
        if (user == null) {
            throw new IllegalArgumentException("Không tìm thấy tài khoản admin");
        }

        if (DEFAULT_SUPER_ADMIN.equals(user.getUserId())) {
            throw new IllegalArgumentException("Không thể xóa tài khoản Super Admin mặc định");
        }
    }

    public static void validateResetPassword(ResetPasswordRequest request, Users user) {
        if (request == null || request.getNewPassword() == null || request.getOldPassword() == null) {
            throw new IllegalArgumentException("Thông tin đổi mật khẩu không được để trống");
        }
        if (user == null) {
            throw new IllegalArgumentException("Không tìm thấy tài khoản người dùng");
        }
        validatePassword(request.getNewPassword());
    }

    public static void validateForceResetPassword(ForceResetPasswordRequest request) {
        if (request == null || request.getNewPassword() == null || request.getTargetUsername() == null) {
            throw new IllegalArgumentException("Thông tin đổi mật khẩu không được để trống");
        }
        validatePassword(request.getNewPassword());
    }

    public static void validateDeleteSuperAdmin(Users user) {
        if (user == null) {
            throw new IllegalArgumentException("Không tìm thấy tài khoản Super Admin");
        }

        if (DEFAULT_SUPER_ADMIN.equals(user.getUserId())) {
            throw new IllegalArgumentException("Không thể xóa tài khoản Super Admin mặc định");
        }
    }

    public static void validateSuperAdminRecovery(SuperAdminRecoveryRequest request, Users superAdmin) {
        if (request == null) {
            throw new IllegalArgumentException("Thông tin khôi phục không được để trống");
        }

        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được để trống");
        }

        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống");
        }

        validatePassword(request.getNewPassword());

        if (superAdmin == null) {
            throw new IllegalArgumentException("Không tìm thấy tài khoản Super Admin");
        }

        // Kiểm tra định dạng email
        if (!Pattern.compile(EMAIL_PATTERN).matcher(request.getEmail()).matches()) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }
    }

    public static void validateSuperAdminPasswordChange(SuperAdminPasswordChangeRequest request, Users superAdmin) {
        if (request == null) {
            throw new IllegalArgumentException("Thông tin đổi mật khẩu không được để trống");
        }

        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được để trống");
        }

        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống");
        }

        if (request.getOldPassword() == null || request.getOldPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu cũ không được để trống");
        }

        validatePassword(request.getNewPassword());

        if (superAdmin == null) {
            throw new IllegalArgumentException("Không tìm thấy tài khoản Super Admin");
        }

        // Kiểm tra định dạng email
        if (!Pattern.compile(EMAIL_PATTERN).matcher(request.getEmail()).matches()) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }

        // Kiểm tra mật khẩu mới khác mật khẩu cũ
        if (request.getOldPassword().equals(request.getNewPassword())) {
            throw new IllegalArgumentException("Mật khẩu mới phải khác mật khẩu cũ");
        }
    }
}