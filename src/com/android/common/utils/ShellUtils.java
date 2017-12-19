package com.android.common.utils;

import android.util.Log;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ShellUtils {
    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_SU = "su";

    public static class CommandResult {
        public String errorMsg;
        public int result;
        public String successMsg;

        public CommandResult(int result) {
            this.result = result;
        }

        public CommandResult(int result, String successMsg, String errorMsg) {
            this.result = result;
            this.successMsg = successMsg;
            this.errorMsg = errorMsg;
        }

        public String toString() {
            return "result = " + this.result + " , successMsg = " + this.successMsg + ", errorMsg = " + this.errorMsg;
        }
    }

    public static boolean checkRootPermission() {
        return execCommand("echo root", true, false).result == 0;
    }

    public static CommandResult execCommand(String command, boolean isRoot) {
        return execCommand(new String[]{command}, isRoot, true);
    }

    public static CommandResult execCommand(List<String> commands, boolean isRoot) {
        return execCommand(commands == null ? null : (String[]) commands.toArray(new String[0]), isRoot, true);
    }

    public static CommandResult execCommand(String[] commands, boolean isRoot) {
        return execCommand(commands, isRoot, true);
    }

    public static CommandResult execCommand(String command, boolean isRoot, boolean isNeedResultMsg) {
        return execCommand(new String[]{command}, isRoot, isNeedResultMsg);
    }

    public static CommandResult execCommand(List<String> commands, boolean isRoot, boolean isNeedResultMsg) {
        return execCommand(commands == null ? null : (String[]) commands.toArray(new String[0]), isRoot, isNeedResultMsg);
    }

    public static CommandResult execCommand(String[] commands, boolean isRoot, boolean isNeedResultMsg) {
        StringBuilder errorMsg;
        IOException e;
        String str;
        String str2;
        Throwable th;
        Exception e2;
        BufferedReader successResult;
        int result = -1;
        if (commands == null || commands.length == 0) {
            return new CommandResult(-1, null, null);
        }
        BufferedReader successResult2 = null;
        BufferedReader errorResult = null;
        StringBuilder stringBuilder = null;
        StringBuilder stringBuilder2 = null;
        DataOutputStream os = null;
        try {
            Process process = Runtime.getRuntime().exec(isRoot ? COMMAND_SU : COMMAND_SH);
            DataOutputStream os2 = new DataOutputStream(process.getOutputStream());
            try {
                for (String command : commands) {
                    if (command != null) {
                        Log.d("shellutil", "command is " + command);
                        os2.write(command.getBytes());
                        os2.writeBytes(COMMAND_LINE_END);
                        os2.flush();
                    }
                }
                os2.writeBytes(COMMAND_EXIT);
                os2.flush();
                Log.d("shellutil", "waiting for result ...");
                result = process.waitFor();
                Log.d("shellutil", "waiting end , isNeedResultMsg " + isNeedResultMsg);
                if (isNeedResultMsg) {
                    StringBuilder successMsg = new StringBuilder();
                    try {
                        errorMsg = new StringBuilder();
                    } catch (IOException e3) {
                        e = e3;
                        os = os2;
                        stringBuilder = successMsg;
                        try {
                            e.printStackTrace();
                            if (os != null) {
                                try {
                                    os.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            if (successResult2 != null) {
                                successResult2.close();
                            }
                            if (errorResult != null) {
                                errorResult.close();
                            }
                            if (stringBuilder != null) {
                                str = null;
                            } else {
                                str = stringBuilder.toString();
                            }
                            if (stringBuilder2 != null) {
                                str2 = null;
                            } else {
                                str2 = stringBuilder2.toString();
                            }
                            return new CommandResult(result, str, str2);
                        } catch (Throwable th2) {
                            th = th2;
                            if (os != null) {
                                try {
                                    os.close();
                                } catch (IOException e42) {
                                    e42.printStackTrace();
                                    throw th;
                                }
                            }
                            if (successResult2 != null) {
                                successResult2.close();
                            }
                            if (errorResult != null) {
                                errorResult.close();
                            }
                            throw th;
                        }
                    } catch (Exception e5) {
                        e2 = e5;
                        os = os2;
                        stringBuilder = successMsg;
                        e2.printStackTrace();
                        if (os != null) {
                            try {
                                os.close();
                            } catch (IOException e422) {
                                e422.printStackTrace();
                            }
                        }
                        if (successResult2 != null) {
                            successResult2.close();
                        }
                        if (errorResult != null) {
                            errorResult.close();
                        }
                        if (stringBuilder != null) {
                            str = stringBuilder.toString();
                        } else {
                            str = null;
                        }
                        if (stringBuilder2 != null) {
                            str2 = stringBuilder2.toString();
                        } else {
                            str2 = null;
                        }
                        return new CommandResult(result, str, str2);
                    } catch (Throwable th3) {
                        th = th3;
                        os = os2;
                        stringBuilder = successMsg;
                        if (os != null) {
                            os.close();
                        }
                        if (successResult2 != null) {
                            successResult2.close();
                        }
                        if (errorResult != null) {
                            errorResult.close();
                        }
                        throw th;
                    }
                    try {
                        successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    } catch (IOException e6) {
                        e422 = e6;
                        os = os2;
                        stringBuilder2 = errorMsg;
                        stringBuilder = successMsg;
                        e422.printStackTrace();
                        if (os != null) {
                            os.close();
                        }
                        if (successResult2 != null) {
                            successResult2.close();
                        }
                        if (errorResult != null) {
                            errorResult.close();
                        }
                        if (stringBuilder != null) {
                            str = null;
                        } else {
                            str = stringBuilder.toString();
                        }
                        if (stringBuilder2 != null) {
                            str2 = null;
                        } else {
                            str2 = stringBuilder2.toString();
                        }
                        return new CommandResult(result, str, str2);
                    } catch (Exception e7) {
                        e2 = e7;
                        os = os2;
                        stringBuilder2 = errorMsg;
                        stringBuilder = successMsg;
                        e2.printStackTrace();
                        if (os != null) {
                            os.close();
                        }
                        if (successResult2 != null) {
                            successResult2.close();
                        }
                        if (errorResult != null) {
                            errorResult.close();
                        }
                        if (stringBuilder != null) {
                            str = stringBuilder.toString();
                        } else {
                            str = null;
                        }
                        if (stringBuilder2 != null) {
                            str2 = stringBuilder2.toString();
                        } else {
                            str2 = null;
                        }
                        return new CommandResult(result, str, str2);
                    } catch (Throwable th4) {
                        th = th4;
                        os = os2;
                        stringBuilder2 = errorMsg;
                        stringBuilder = successMsg;
                        if (os != null) {
                            os.close();
                        }
                        if (successResult2 != null) {
                            successResult2.close();
                        }
                        if (errorResult != null) {
                            errorResult.close();
                        }
                        throw th;
                    }
                    try {
                        Log.d("shellutil", "successResult ");
                        BufferedReader errorResult2 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                        try {
                            String s;
                            Log.d("shellutil", "errorResult ");
                            while (true) {
                                s = successResult.readLine();
                                if (s == null) {
                                    break;
                                }
                                successMsg.append(s);
                            }
                            while (true) {
                                s = errorResult2.readLine();
                                if (s == null) {
                                    break;
                                }
                                errorMsg.append(s);
                            }
                            Log.d("shellutil", "successMsg " + successMsg);
                            Log.d("shellutil", "errorMsg " + errorMsg);
                            stringBuilder2 = errorMsg;
                            stringBuilder = successMsg;
                            errorResult = errorResult2;
                            successResult2 = successResult;
                        } catch (IOException e8) {
                            e422 = e8;
                            os = os2;
                            stringBuilder2 = errorMsg;
                            stringBuilder = successMsg;
                            errorResult = errorResult2;
                            successResult2 = successResult;
                        } catch (Exception e9) {
                            e2 = e9;
                            os = os2;
                            stringBuilder2 = errorMsg;
                            stringBuilder = successMsg;
                            errorResult = errorResult2;
                            successResult2 = successResult;
                        } catch (Throwable th5) {
                            th = th5;
                            os = os2;
                            stringBuilder2 = errorMsg;
                            stringBuilder = successMsg;
                            errorResult = errorResult2;
                            successResult2 = successResult;
                        }
                    } catch (IOException e10) {
                        e422 = e10;
                        os = os2;
                        stringBuilder2 = errorMsg;
                        stringBuilder = successMsg;
                        successResult2 = successResult;
                        e422.printStackTrace();
                        if (os != null) {
                            os.close();
                        }
                        if (successResult2 != null) {
                            successResult2.close();
                        }
                        if (errorResult != null) {
                            errorResult.close();
                        }
                        if (stringBuilder != null) {
                            str = null;
                        } else {
                            str = stringBuilder.toString();
                        }
                        if (stringBuilder2 != null) {
                            str2 = null;
                        } else {
                            str2 = stringBuilder2.toString();
                        }
                        return new CommandResult(result, str, str2);
                    } catch (Exception e11) {
                        e2 = e11;
                        os = os2;
                        stringBuilder2 = errorMsg;
                        stringBuilder = successMsg;
                        successResult2 = successResult;
                        e2.printStackTrace();
                        if (os != null) {
                            os.close();
                        }
                        if (successResult2 != null) {
                            successResult2.close();
                        }
                        if (errorResult != null) {
                            errorResult.close();
                        }
                        if (stringBuilder != null) {
                            str = stringBuilder.toString();
                        } else {
                            str = null;
                        }
                        if (stringBuilder2 != null) {
                            str2 = stringBuilder2.toString();
                        } else {
                            str2 = null;
                        }
                        return new CommandResult(result, str, str2);
                    } catch (Throwable th6) {
                        th = th6;
                        os = os2;
                        stringBuilder2 = errorMsg;
                        stringBuilder = successMsg;
                        successResult2 = successResult;
                        if (os != null) {
                            os.close();
                        }
                        if (successResult2 != null) {
                            successResult2.close();
                        }
                        if (errorResult != null) {
                            errorResult.close();
                        }
                        throw th;
                    }
                }
                if (os2 != null) {
                    try {
                        os2.close();
                    } catch (IOException e4222) {
                        e4222.printStackTrace();
                    }
                }
                if (successResult2 != null) {
                    successResult2.close();
                }
                if (errorResult != null) {
                    errorResult.close();
                    os = os2;
                    if (stringBuilder != null) {
                        str = null;
                    } else {
                        str = stringBuilder.toString();
                    }
                    if (stringBuilder2 != null) {
                        str2 = null;
                    } else {
                        str2 = stringBuilder2.toString();
                    }
                    return new CommandResult(result, str, str2);
                }
                os = os2;
            } catch (IOException e12) {
                e4222 = e12;
                os = os2;
            } catch (Exception e13) {
                e2 = e13;
                os = os2;
            } catch (Throwable th7) {
                th = th7;
                os = os2;
            }
        } catch (IOException e14) {
            e4222 = e14;
            e4222.printStackTrace();
            if (os != null) {
                os.close();
            }
            if (successResult2 != null) {
                successResult2.close();
            }
            if (errorResult != null) {
                errorResult.close();
            }
            if (stringBuilder != null) {
                str = null;
            } else {
                str = stringBuilder.toString();
            }
            if (stringBuilder2 != null) {
                str2 = null;
            } else {
                str2 = stringBuilder2.toString();
            }
            return new CommandResult(result, str, str2);
        } catch (Exception e15) {
            e2 = e15;
            e2.printStackTrace();
            if (os != null) {
                os.close();
            }
            if (successResult2 != null) {
                successResult2.close();
            }
            if (errorResult != null) {
                errorResult.close();
            }
            if (stringBuilder != null) {
                str = stringBuilder.toString();
            } else {
                str = null;
            }
            if (stringBuilder2 != null) {
                str2 = stringBuilder2.toString();
            } else {
                str2 = null;
            }
            return new CommandResult(result, str, str2);
        }
        if (stringBuilder != null) {
            str = stringBuilder.toString();
        } else {
            str = null;
        }
        if (stringBuilder2 != null) {
            str2 = stringBuilder2.toString();
        } else {
            str2 = null;
        }
        return new CommandResult(result, str, str2);
    }
}
