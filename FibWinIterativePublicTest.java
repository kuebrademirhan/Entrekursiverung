import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class FibWinIterativePublicTest {
	// ========== ORIGINAL RECURSIVE SOLUTION ==========
	private static long fibWinRec(int x, int win) {
		if (x < win + 1) {
			return x;
		}
		return fibWinRec(x - (1 + win), win) + fibWinRec(x - 1, win);
	}

	// ========== SYSTEM ==========
	protected static final String EX_NAME_FibWinIterative = "FibWinIterative";
	protected static final String CLASS_NAME_FibWinIterative = "FibWinIterative";
	protected static final String METHOD_NAME_fibWinIterative = "fibWinIterative";
	// --------------------
	// ========== TEST-DATA ==========
	private final Random RND = new Random(4711_0815_666L);

	// ========== CHECK INTESTINES ==========
	@Test(timeout = 666)
	public void pubTest__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines();
	}

	// ========== PUBLIC TEST ==========
	@Test(timeout = 10666)
	public void pubTest__FibWin__random__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		// ---------- base cases only ----------
		for (int win = 1; win <= 100; win++) {
			for (int x = 0; x <= win; x++) {
				FibWinIterativePublicTest.check(x, win, x);
			}
			FibWinIterativePublicTest.check(win + 1, win, win);
		}
		// ---------- classic fibonacci ----------
		long f = 0, fn = 1;
		for (int x = 0; x <= 200; x++) { // long-overflow above x = 92 expected - but formula is formula... ;)
			FibWinIterativePublicTest.check(x, 1, f);
			fn = f + (f = fn);
		}
		// ---------- random ----------
		for (int pass = 0; pass < 200; pass++) {
			for (int i = 0; i < RND_MAX; i++) {
				FibWinIterativePublicTest.check(RND_X[i], RND_WIN[i], RND_FIB[i]);
			}
		}
	}

	// ========== HELPER ==========
	static void check(int x, int win, long expected) {
		String msg = CLASS_NAME_FibWinIterative + "." + METHOD_NAME_fibWinIterative + "(..., " + x + ", " + win + ") failed";
		BigBrother fwh = new BigBrother();
		long actual = FibWinIterative.fibWinIterative(fwh, x, win);
		assertFalse(msg + ": seems to be recursive.", fwh.mainCalledMoreThanOnce);
		assertEquals(msg + ": allocated wrong number of arrays.", x <= win ? 0 : 1, fwh.callsToAllocateArray);
		assertTrue(msg + ": allocated array has wrong size.", x <= win ? (fwh.fib == null) : (fwh.fib != null && fwh.fib.length <= win + 2));
		assertEquals(msg + ": wrong return value.", expected, actual);
	}

	private final int RND_MAX = 5;
	private final int[] RND_X = new int[RND_MAX];
	private final int[] RND_WIN = new int[RND_MAX];
	private final long[] RND_FIB = new long[RND_MAX];

	{
		for (int i = 0; i < RND_MAX; i++) {
			RND_X[i] = 25 + RND.nextInt(25);
			RND_WIN[i] = 2 + RND.nextInt(12);
			RND_FIB[i] = fibWinRec(RND_X[i], RND_WIN[i]);
		}
	}

	static final class BigBrother implements FibWinHelper {
		private long[] fib;
		private boolean mainCalledMoreThanOnce = false;
		private long callsToAllocateArray = 0, callsToArrayGetter = 0, callsToArraySetter = 0;

		@Override
		public void allocateArray(int size) {
			callsToAllocateArray++;
			inspectStack();
			fib = new long[size];
		}

		@Override
		public long getArrayValue(int index) {
			callsToArrayGetter++;
			if (callsToArrayGetter < 20 || callsToArrayGetter % 7 == 0) {
				inspectStack();
			}
			return fib[index];
		}

		@Override
		public void setArrayValue(int index, long newValue) {
			callsToArraySetter++;
			if (callsToArraySetter < 20 || callsToArraySetter % 7 == 0) {
				inspectStack();
			}
			fib[index] = newValue;
		}

		private void inspectStack() {
			int stackDepthMain = 0;
			StackTraceElement[] st = Thread.currentThread().getStackTrace();
			for (StackTraceElement ste : st) {
				if (ste.getClassName().equals(FibWinIterativePublicTest.CLASS_NAME_FibWinIterative)) {
					if (ste.getMethodName().equals(FibWinIterativePublicTest.METHOD_NAME_fibWinIterative)) {
						stackDepthMain++;
					}
				}
			}
			mainCalledMoreThanOnce |= stackDepthMain > 1;
		}
	}

	// ========== HELPER: Intestines ==========
	// @AuD-STUDENT: DO NOT USE REFLECTION IN YOUR OWN SUBMISSION!
	private static Class<?>[] getDeclaredClasses(Class<?> clazz) {
		java.util.List<Class<?>> declaredClasses = new java.util.ArrayList<>();
		for (Class<?> c : clazz.getDeclaredClasses()) {
			if (!c.isSynthetic()) {
				declaredClasses.add(c);
			}
		}
		return declaredClasses.toArray(new Class[0]);
	}

	private static Field[] getDeclaredFields(Class<?> clazz) {
		java.util.List<Field> declaredFields = new java.util.ArrayList<>();
		for (Field f : clazz.getDeclaredFields()) {
			if (!f.isSynthetic()) {
				declaredFields.add(f);
			}
		}
		return declaredFields.toArray(new Field[0]);
	}

	private static Constructor<?>[] getDeclaredConstructors(Class<?> clazz) {
		java.util.List<Constructor<?>> declaredConstructors = new java.util.ArrayList<>();
		for (Constructor<?> c : clazz.getDeclaredConstructors()) {
			if (!c.isSynthetic()) {
				declaredConstructors.add(c);
			}
		}
		return declaredConstructors.toArray(new Constructor[0]);
	}

	private static Method[] getDeclaredMethods(Class<?> clazz) {
		java.util.List<Method> declaredMethods = new java.util.ArrayList<>();
		for (Method m : clazz.getDeclaredMethods()) {
			if (!m.isBridge() && !m.isSynthetic()) {
				declaredMethods.add(m);
			}
		}
		return declaredMethods.toArray(new Method[0]);
	}

	private void check__Intestines() {
		Class<FibWinIterative> clazz = FibWinIterative.class;
		assertTrue(clazz + " must be public!", Modifier.isPublic(clazz.getModifiers()));
		assertFalse(clazz + " must not be abstract!", Modifier.isAbstract(clazz.getModifiers()));
		assertFalse(clazz + " must not be an annotation!", clazz.isAnnotation());
		assertFalse(clazz + " must not be an enumeration!", clazz.isEnum());
		assertFalse(clazz + " must not be an interface!", clazz.isInterface());
		assertSame(clazz + " must extend a certain super-class!", Object.class, clazz.getSuperclass());
		assertEquals(clazz + " must implement a certain number of interfaces!", 0, clazz.getInterfaces().length);
		assertSame(clazz + " must implement a certain number of interfaces!", 0, clazz.getInterfaces().length);
		assertEquals(clazz + " must declare a certain number of inner annotations!", 0, clazz.getDeclaredAnnotations().length);
		assertEquals(clazz + " must declare a certain number of inner classes!", 0, getDeclaredClasses(clazz).length);
		Field[] fields = getDeclaredFields(clazz);
		assertEquals(clazz + " must declare a certain number of fields!", 0, fields.length);
		Constructor<?>[] constructors = getDeclaredConstructors(clazz);
		assertEquals(clazz + " must declare a certain number of constructors (possibly including default constructor)!", 1, constructors.length);
		for (Constructor<?> constructor : constructors) {
			assertTrue(constructor + " - constructor must be public!", Modifier.isPublic(constructor.getModifiers()));
			assertEquals(constructor + " - constructor must have a certain number of parameters!", 0, constructors[0].getParameterTypes().length);
		}
		Method[] methods = getDeclaredMethods(clazz);
		assertEquals(clazz + " must declare a certain number of methods!", 1, methods.length);
		for (Method method : methods) {
			assertTrue(method + " - method must be public!", Modifier.isPublic(method.getModifiers()));
			assertTrue(method + " - method must be static!", Modifier.isStatic(method.getModifiers()));
			assertEquals(method + " - method must have a certain name!", METHOD_NAME_fibWinIterative, method.getName());
		}
	}
}